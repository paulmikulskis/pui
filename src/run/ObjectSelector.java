package run;

import java.util.ArrayList;

import geometry.GeometryTester;
import processing.core.PApplet;
import slider.HorizontalContinuousSlider;
import slider.VerticalDiscreteSlider;
import slider.VerticalContinuousSlider;

public class ObjectSelector extends PApplet{

	PApplet p;
	ArrayList<Object> items;
	int mouseX,mouseY;
	GeometryTester tester;
	
	
	public ObjectSelector(PApplet p) {
		this.p = p;
		items = new ArrayList<Object>();
		tester = new GeometryTester();
	}
	public void register(Object o) {
		items.add(o);
	}
	public void listen() {
		mouseX = p.mouseX;
		mouseY = p.mouseY;
		for(Object o : items) {
			switch(o.getClass().toString()) {
			
			//-------Case if the GUI item is a HorizontalContinuousSlider-------
			case "class slider.HorizontalContinuousSlider":
					if(tester.insideCircle(((HorizontalContinuousSlider)o).indicator[0], ((HorizontalContinuousSlider)o).indicator[1], (((HorizontalContinuousSlider)o).indicator[2])/2, mouseX, mouseY)) {
						((HorizontalContinuousSlider)o).mouseOver = true;
					}
					else {
						((HorizontalContinuousSlider)o).mouseOver = false;
					}
					
					if(p.mousePressed) {
						((HorizontalContinuousSlider)o).mousePress = true;
					}
					else {
						((HorizontalContinuousSlider)o).mousePress = false;
					}
				break;
			
			//-------Case if the GUI item is a HorizontalDiscreteSlider-------
			case "class slider.HorizontalDiscreteSlider":
				if(tester.insideCircle(((VerticalDiscreteSlider)o).indicator[0], ((VerticalDiscreteSlider)o).indicator[1], (((VerticalDiscreteSlider)o).indicator[2])/2, mouseX, mouseY)) {
					((VerticalDiscreteSlider)o).mouseOver = true;
				}
				else {
					((VerticalDiscreteSlider)o).mouseOver = false;
				}
				
				if(p.mousePressed) {
					((VerticalDiscreteSlider)o).mousePress = true;
				}
				else {
					((VerticalDiscreteSlider)o).mousePress = false;
				}
			break;
			
			//-------Case if the GUI item is a VerticalDiscreteSlider-------
			case "class slider.VerticalDiscreteSlider":
				if(tester.insideCircle(((VerticalDiscreteSlider)o).indicator[0], ((VerticalDiscreteSlider)o).indicator[1], (((VerticalDiscreteSlider)o).indicator[2])/2, mouseX, mouseY)) {
					((VerticalDiscreteSlider)o).mouseOver = true;
				}
				else {
					((VerticalDiscreteSlider)o).mouseOver = false;
				}
				
				if(p.mousePressed) {
					((VerticalDiscreteSlider)o).mousePress = true;
				}
				else {
					((VerticalDiscreteSlider)o).mousePress = false;
				}
			break;
			
			//-------Case if the GUI item is a VerticalContinuousSlider-------
			case "class slider.VerticalContinuousSlider":
				if(tester.insideCircle(((VerticalContinuousSlider)o).indicator[0], ((VerticalContinuousSlider)o).indicator[1], (((VerticalContinuousSlider)o).indicator[2])/2, mouseX, mouseY)) {
					((VerticalContinuousSlider)o).mouseOver = true;
				}
				else {
					((VerticalContinuousSlider)o).mouseOver = false;
				}
				
				if(p.mousePressed) {
					((VerticalContinuousSlider)o).mousePress = true;
				}
				else {
					((VerticalContinuousSlider)o).mousePress = false;
				}
			break;
			}
		}
	}
	public void draw() {
		p.background(120);
		for(Object o : items) {
			((PApplet) o).draw();
		}
	}
}