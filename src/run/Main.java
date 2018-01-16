package run;

import processing.core.PApplet;
import slider.HorizontalContinuousSlider;
import slider.HorizontalDiscreteSlider;
import slider.VerticalContinuousSlider;

public class Main extends PApplet{
	
	VerticalContinuousSlider slider1;
	HorizontalContinuousSlider slider2;
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
    	slider1 = new VerticalContinuousSlider(20,200,100,30,this);
    	//slider2 = new HorizontalContinuousSlider (20,400,600,30,this);
    	slider1.toggleDisplay();
    	//slider2.toggleDisplay();
    	o.register(slider1);
    	//o.register(slider2);
    }

    public void draw(){
    	o.listen();
    	o.draw();
    }
	
}
