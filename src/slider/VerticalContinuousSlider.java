package slider;

import processing.core.PApplet;

public class VerticalContinuousSlider extends PApplet{

	
	//Slider Bar properties
	int sliderX,sliderY,sliderLength;
	int bottomValue,topValue;
	int indicatorSize;
	public int[] indicator;
	public int[] trackColor;
	public int[] sliderColor;
	public int[] sliderColorSelect;
	//Control Variables
	PApplet p;
	public Boolean mouseOver;
	public Boolean mousePress;
	public Boolean active;
	//Value Display Variables
	public Boolean displayValue;
	public int displayValueX,displayValueY;
	
	//Constructors-----------------------------------
	public VerticalContinuousSlider(int sliderXPos, int sliderYPos, PApplet p) {
		this.p = p;
		sliderX = sliderXPos;
		sliderY = sliderYPos;
		bottomValue = 0;
		topValue = 100;
		sliderLength = 200;
		this.indicatorSize = 15;
		this.setDefaultValues();
	}
	//Constructs a slider at the given x and y value, with a circular indicator specified by indicatorSize
	public VerticalContinuousSlider(int sliderXPos, int sliderYPos, int indicatorSize, PApplet p) {
		this.p = p;
		sliderX = sliderXPos;
		sliderY = sliderYPos;
		bottomValue = 0;
		topValue = 100;
		sliderLength = 200;
		this.indicatorSize = indicatorSize;
		this.setDefaultValues();
	}
	//Constructs a slider at the given x and y value with length l, with a circular indicator specified by indicatorSize
	public VerticalContinuousSlider(int sliderXPos, int slideYPos, int length, int indicatorSize, PApplet p) {
		this.p = p;
		sliderX = sliderXPos;
		sliderY = slideYPos;
		bottomValue = 0;
		topValue = 100;
		sliderLength = length;
		this.indicatorSize = indicatorSize;
		this.setDefaultValues();
	}
	//Constructs a slider at the given x and y value with length l, with a circular indicator specified by indicatorSize
	//And allows user to choose to display the value of the slider if they desire
	public VerticalContinuousSlider(int sliderXPos, int slideYPos, int length, int indicatorSize, int minValue, int maxValue, PApplet p) {
			this.p = p;
			sliderX = sliderXPos;
			sliderY = slideYPos;
			bottomValue = minValue;
			topValue = maxValue;
			sliderLength = length;
			this.indicatorSize = indicatorSize;
			this.setDefaultValues();
		}
	//Constructor default value setter
	private void setDefaultValues() {
		trackColor = new int[]{204,51,153,40};
		sliderColor = new int[] {122,31,92};
		sliderColorSelect = new int[] {204,51,153};
		displayValue = false;
		mouseOver = false;
		mousePress = false;
		active = false;
		indicator = new int[4];
		indicator[0] = sliderX;
		indicator[1] = sliderY;
		indicator[2] = indicatorSize;
		indicator[3] = indicatorSize;
		displayValueX = sliderX + 30;
		displayValueY = sliderY;
	}
	//Getters----------------------------------------
	public float getValue() {
		float slope = (((float)topValue - (float)bottomValue) / ((float)sliderLength));
		return (-(indicator[1] - sliderY) * slope) + bottomValue;
	}
	//Setters----------------------------------------
	public void setDisplayPosition(int x, int y) {
		this.displayValueX = x;
		this.displayValueY = y;
	}
	public void toggleDisplay() {
		if(displayValue == false) {
			displayValue = true;
		}
		else displayValue = false;
	}
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
	public void setSliderMaxValue(int i) {

		topValue = i;
	}
	public void setSliderMinValue(int j) {
		bottomValue = j;
	}
	public void setSliderPosition(int x, int y) {
		sliderX = x;
		sliderY = y;
	}
	//Draw method------------------------------------
	public void draw() {
		
		if(displayValue) {
			p.fill(100);
			p.rect(displayValueX-3, displayValueY-16, 150, 30);
			String value = Integer.toString((int)getValue());
			p.textSize(30);
			p.fill(0);
			p.text(value, displayValueX, displayValueY+9);
		}
		p.strokeWeight(3);
		p.fill(trackColor[0], trackColor[1], trackColor[2], trackColor[3]);
		p.rect(sliderX-5, sliderY, 10, -sliderLength,8,8,8,8);
		if(!mouseOver && mousePress && !active) {
			p.fill(sliderColor[0], sliderColor[1], sliderColor[2]);
			p.ellipse(indicator[0], indicator[1], indicator[2], indicator[3]);
		}
		if(mouseOver && !mousePress && !active) {
			p.fill(sliderColor[0], sliderColor[1], sliderColor[2]);
			p.ellipse(indicator[0], indicator[1], (int)(indicator[2]*1.25), (int)(indicator[3]*1.25));
		}
		if(active) {
			if(!p.mousePressed) {
				active = false;
			}
			else {
				//Find where the indicator should go, within bounds of the slider
				int sliderYfromMouse;
				if(p.mouseY > sliderY) {
					sliderYfromMouse = sliderY;
				}
				else if(p.mouseY < sliderY - sliderLength) {
					sliderYfromMouse = sliderY - sliderLength;
				}
				else sliderYfromMouse = p.mouseY;
				indicator[1] = sliderYfromMouse;
				p.fill(sliderColorSelect[0], sliderColorSelect[1], sliderColorSelect[2]);
				p.ellipse(indicator[0], indicator[1], (int)(indicator[2]*1.25), (int)(indicator[3]*1.25));
			}
		}
		if(mouseOver && mousePress && !active) {
			active = true;
		}
		if(!mouseOver && !mousePress && !active) {
			p.fill(sliderColor[0], sliderColor[1], sliderColor[2]);
			p.ellipse(indicator[0], indicator[1], indicator[2], indicator[3]);
		}

		
	}
}
