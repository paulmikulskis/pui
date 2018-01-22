package slider;

import processing.core.PApplet;

public class HorizontalContinuousSlider extends PApplet{
	
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
	public Boolean sleek;
	
	//Constructors-----------------------------------
	public HorizontalContinuousSlider(int sliderXPos, int sliderYPos, PApplet p) {
		this.p = p;
		sliderX = sliderXPos;
		sliderY = sliderYPos;
		bottomValue = 0;
		topValue = 100;
		sliderLength = 200;
		displayValueX = sliderX + sliderLength + 30;
		displayValueY = sliderY;
		this.indicatorSize = 15;
		this.setDefaultValues();
	}
	//Constructs a slider at the given x and y value, with a circular indicator specified by indicatorSize
	public HorizontalContinuousSlider(int sliderXPos, int sliderYPos, int indicatorSize, PApplet p) {
		this.p = p;
		sliderX = sliderXPos;
		sliderY = sliderYPos;
		bottomValue = 0;
		topValue = 100;
		sliderLength = 200;
		displayValueX = sliderX + sliderLength + 30;
		displayValueY = sliderY;
		this.indicatorSize = indicatorSize;
		this.setDefaultValues();
	}
	//Constructs a slider at the given x and y value with length l, with a circular indicator specified by indicatorSize
	public HorizontalContinuousSlider(int sliderXPos, int slideYPos, int length, int indicatorSize, PApplet p) {
		this.p = p;
		sliderX = sliderXPos;
		sliderY = slideYPos;
		bottomValue = 0;
		topValue = 100;
		sliderLength = length;
		displayValueX = sliderX + sliderLength + 30;
		displayValueY = sliderY;
		this.indicatorSize = indicatorSize;
		this.setDefaultValues();
	}
	//Constructs a slider at the given x and y value with length l, with a circular indicator specified by indicatorSize
	//And allows user to choose to display the value of the slider if they desire
	public HorizontalContinuousSlider(int sliderXPos, int slideYPos, int length, int indicatorSize, int minValue, int maxValue, PApplet p) {
			this.p = p;
			sliderX = sliderXPos;
			sliderY = slideYPos;
			bottomValue = minValue;
			topValue = maxValue;
			sliderLength = length;
			displayValueX = sliderX + sliderLength + 30;
			displayValueY = sliderY + 19;
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
		sleek = false;
	}
	//Getters----------------------------------------
	public float getValue() {
		float slope = (((float)topValue - (float)bottomValue) / ((float)sliderLength));
		return ((indicator[0]-sliderX) * slope) + bottomValue;
	}
	//Setters----------------------------------------
	public void sleek() {
		if(sleek) {
			sleek = false;
		}
		else {
			sleek = true;
		}
	}
	
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
		
		if(sleek) {
			p.stroke(0,0,0);
			p.rect(indicator[0], sliderY-1, sliderLength - (indicator[0]-sliderX), 2);
			p.stroke(trackColor[0], trackColor[1], trackColor[2]);
			p.rect(sliderX, sliderY-1, indicator[0]-sliderX, 2);
			p.stroke(0,0,0);
		}
		else {
			p.fill(trackColor[0], trackColor[1], trackColor[2], trackColor[3]);
			p.rect(sliderX, sliderY-5, 10, sliderLength,8,8,8,8);
		}
			
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
				int sliderXfromMouse;
				if(p.mouseX < sliderX) {
					sliderXfromMouse = sliderX;
				}
				else if(p.mouseX > sliderX + sliderLength) {
					sliderXfromMouse = sliderX+sliderLength;
				}
				else sliderXfromMouse = p.mouseX;
				indicator[0] = sliderXfromMouse;
				p.fill(sliderColorSelect[0], sliderColorSelect[1], sliderColorSelect[2]);
				p.ellipse(sliderXfromMouse, indicator[1], (int)(indicator[2]*1.25), (int)(indicator[3]*1.25));
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
