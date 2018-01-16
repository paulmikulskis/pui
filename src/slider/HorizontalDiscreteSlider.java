package slider;

import processing.core.PApplet;

public class HorizontalDiscreteSlider extends PApplet{
	
	//Slider Bar properties
	int sliderX,sliderY,sliderLength;
	int bottomValue,topValue,subdivisions;
	int indicatorSize;
	int tickMarkDistance;
	int[] values;
	int indicatorMoving;
	int indicatorDestination;
	public int[] indicator;
	private int[] trackColor;
	private int[] sliderColor;
	private int[] sliderColorSelect;
	//Control Variables
	PApplet p;
	public Boolean mouseOver;
	public Boolean mousePress;
	public Boolean active;
	Boolean tickValues;
	//Value Display Variables
	public Boolean displayValue;
	public int displayValueX,displayValueY;
	
	//Constructors-----------------------------------
	//Constructs a slider at the given x and y value, with n subdivisions
	public HorizontalDiscreteSlider(int sliderXPos, int sliderYPos, int subdivisions, PApplet p) {
		this.subdivisions = subdivisions;
		this.p = p;
		this.sliderX = sliderXPos;
		this.sliderY = sliderYPos;
		this.bottomValue = 0;
		this.topValue = 100;
		this.sliderLength = 200;
		setDefaultValues();
	}
	//Constructs a slider at the given x and y value with length l, with n subdivisions
	public HorizontalDiscreteSlider(int sliderXPos, int slideYPos, int length, int subdivisions, PApplet p) {
		this.subdivisions = subdivisions;
		this.p = p;
		sliderX = sliderXPos;
		sliderY = slideYPos;
		bottomValue = 0;
		topValue = 100;
		sliderLength = length;
		setDefaultValues();
	}
	//Constructs a slider at the given x and y value with length l, with minValue and maxValue of slider
	public HorizontalDiscreteSlider(int sliderXPos, int slideYPos, int length, int subdivisions, int minValue, int maxValue, PApplet p) {
			this.subdivisions = subdivisions;		
			this.p = p;
			sliderX = sliderXPos;
			sliderY = slideYPos;
			bottomValue = minValue;
			topValue = maxValue;
			setDefaultValues();
		}
	private void setDefaultValues(){
		//Sets the value places based on slider length and number of values
		int step = (int) (((float)topValue - (float)bottomValue) / ((float)subdivisions));
		values = new int[subdivisions + 1];
		values[0] = bottomValue;
		for(int i = 1; i < subdivisions + 1; i++) {
			values[i] = values[i-1] + step;
		}
		//Sets other default values common to all Discrete Sliders
		tickMarkDistance = (int) (((float)sliderLength) / ((float)subdivisions));
		indicatorMoving = 0;
		indicatorSize = 30;
		tickValues = true;
		trackColor = new int[]{204,51,153,40};
		sliderColor = new int[] {122,31,92};
		sliderColorSelect = new int[] {204,51,153};
		displayValue = false;
		mouseOver = false;
		mousePress = false;
		active = false;
		indicator = new int[5];
		indicator[0] = sliderX;
		indicator[1] = sliderY;
		indicator[2] = indicatorSize;
		indicator[3] = indicatorSize;
		displayValueX = sliderX + sliderLength + 30;
		displayValueY = sliderY;
		indicatorDestination = indicator[0];
	}
	
	//Getters----------------------------------------
	public float getValueOfSlider() {
		return values[(int) ((((float)(indicator[0] - sliderX)) / (float)sliderLength) * subdivisions)];
	}

	//Setters----------------------------------------
	public void setTrackColor(int r, int g, int b, int alpha) {
		trackColor[0] = r;
		trackColor[1] = g;
		trackColor[2] = b;
		trackColor[3] = alpha;
	}
	public void setSliderColor(int r, int g, int b) {
		sliderColor[0] = r;
		sliderColor[1] = g;
		sliderColor[2] = b;
	}
	public void setSliderSelectColor(int r, int g, int b) {
		sliderColor[0] = r;
		sliderColor[1] = g;
		sliderColor[2] = b;
	}
	public void setMaximumValue(int i) {
		topValue = i;
	}
	public void setMinimumValue(int j) {
		bottomValue = j;
	}
	public void setPosition(int x, int y) {
		sliderX = x;
		sliderY = y;
	}
	public void toggleDisplay() {
		if(displayValue == false) {
			displayValue = true;
		}
		else displayValue = false;
	}
	public void displayTickMarks(Boolean b) {
		this.tickValues = b;
	}
	//Draw method------------------------------------
	public void draw() {
		//If slider is set to display the value, then draw it and fill it with slider's value
		if(displayValue) {
			p.fill(100);
			p.rect(displayValueX-3, displayValueY-20, 150, 30);
			String value = Integer.toString((int)getValueOfSlider());
			p.textSize(30);
			p.fill(0);
			p.text(value, displayValueX, displayValueY+5);
		}
		
		//Draws the track of the slider 
		p.strokeWeight(3);
		p.fill(trackColor[0], trackColor[1], trackColor[2], trackColor[3]);
		p.rect(sliderX, sliderY-5, sliderLength, 10,8,8,8,8);
		
		//Draws slider tick marks
		if(tickValues) {
			for(int i = sliderX + tickMarkDistance; i < sliderX + sliderLength; i = i + tickMarkDistance) {
				p.line(i, sliderY-10, i, sliderY+10);
			}
		}
		//If the mouse is pressed on the indicator, do nothing and draw inactive slider
		if(!mouseOver && mousePress && !active) {
			p.fill(sliderColor[0], sliderColor[1], sliderColor[2]);
			p.ellipse(indicator[0], indicator[1], indicator[2], indicator[3]);
		}
		
		//If the mouse is over and not pressed, enlarge the indicator by a scale of 1.25
		if(mouseOver && !mousePress && !active) {
			p.fill(sliderColor[0], sliderColor[1], sliderColor[2]);
			p.ellipse(indicator[0], indicator[1], (int)(indicator[2]*1.25), (int)(indicator[3]*1.25));
		}
		
		//If the slider is active (i.e. mouse is held down after being on indicator)
		if(active && indicatorMoving == 0) {
			if(!p.mousePressed) {
				active = false;
			}
			else {
				int sliderXfromMouse;
				if(p.mouseX < sliderX) {
					sliderXfromMouse = sliderX;
				}
				else if(p.mouseX > sliderX + sliderLength) {
					sliderXfromMouse = sliderX+sliderLength;
				}
				else sliderXfromMouse = p.mouseX;
				if( (Math.abs( indicator[0] - sliderXfromMouse )) > (tickMarkDistance / 2) && 
						(Math.abs( indicator[0] - sliderXfromMouse )) < (tickMarkDistance)) {
					indicator[4] = indicator[0];
					if(sliderXfromMouse < indicator[0]) {
						indicatorMoving = -1;
						indicatorDestination = indicator[0] - tickMarkDistance;
					}
					if(sliderXfromMouse > indicator[0]) {
						indicatorMoving = 1;
						indicatorDestination = indicator[0] + tickMarkDistance;
					}
				}
				if( (Math.abs( indicator[0] - sliderXfromMouse )) > tickMarkDistance) {
					indicator[4] = indicator[0];
					if(sliderXfromMouse < indicator[0]) {
						indicatorMoving = -1;
						indicatorDestination = indicator[0] - ( ((int)( (Math.abs( indicator[0] - sliderXfromMouse ) / tickMarkDistance )) * tickMarkDistance ));
					}
					if(sliderXfromMouse > indicator[0]) {
						indicatorMoving = 1;
						indicatorDestination = indicator[0] + ( ((int)( (Math.abs( indicator[0] - sliderXfromMouse ) / tickMarkDistance )) * tickMarkDistance ));
					}
				}
				p.fill(sliderColorSelect[0], sliderColorSelect[1], sliderColorSelect[2]);
				p.ellipse(indicator[0], indicator[1], (int)(indicator[2]*1.25), (int)(indicator[3]*1.25));	
			}
		}
		if(active && indicatorMoving == -1) {
			int dx = (indicatorDestination) - indicator[0];
			indicator[0] += (int)(dx * 0.3);
			if( Math.abs( dx * 0.05) < 1) {
				indicator[0] -= 1;
			}
			p.fill(sliderColorSelect[0], sliderColorSelect[1], sliderColorSelect[2]);
			p.ellipse(indicator[0], indicator[1], (int)(indicator[2]*1.25), (int)(indicator[3]*1.25));
			if(indicator[0] == (indicatorDestination)) {
				indicator[4] = indicator[0];
				indicatorMoving = 0;
			}
		}
		if(active && indicatorMoving == 1) {
			int dx = (indicatorDestination) - indicator[0];
			indicator[0] += (int)(dx * 0.3);
			if( Math.abs( dx * 0.05) < 1) {
				indicator[0] += 1;
			}
			p.fill(sliderColorSelect[0], sliderColorSelect[1], sliderColorSelect[2]);
			p.ellipse(indicator[0], indicator[1], (int)(indicator[2]*1.25), (int)(indicator[3]*1.25));
			
			if(indicator[0] == (indicatorDestination)) {
				indicator[4] = indicator[0];
				indicatorMoving = 0;
			}
		}
		
		//If the mouse is over the slider and it gets pressed, set the slider to active 
		if(mouseOver && mousePress && !active) {
			active = true;
		}
		
		//If the mouse is not over the slider and it is not pressed and the slider is not active, do nothing
		if(!mouseOver && !mousePress && !active) {
			p.fill(sliderColor[0], sliderColor[1], sliderColor[2]);
			p.ellipse(indicator[0], indicator[1], indicator[2], indicator[3]);
		}	
	}

}
