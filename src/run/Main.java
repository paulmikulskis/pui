package run;

import processing.core.PApplet;
import slider.HorizontalContinuousSlider;
import slider.HorizontalDiscreteSlider;
import slider.VerticalDiscreteSlider;
import slider.VerticalContinuousSlider;

public class Main extends PApplet{
	
	VerticalDiscreteSlider slider1;
	HorizontalDiscreteSlider slider2;
	ObjectSelector o;
	
	public static void main(String[] args) {
		PApplet.main("run.Main");
	}
	
    public void settings(){
    	size(1000,500);
    }

    public void setup(){
    	fill(120,50,240);
    	o = new ObjectSelector(this);
    	slider2 = new HorizontalDiscreteSlider(20,200,400,4,this);
    	//slider2 = new HorizontalContinuousSlider (20,400,600,30,this);
    	slider2.displayTickMarks(false);
    	slider2.toggleDisplay();
    	slider2.sleek();
    	//slider2.toggleDisplay();
    	o.register(slider2);
    	//o.register(slider2);
    }

    public void draw(){
    	o.run();
    }
	
}
