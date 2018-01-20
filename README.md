# PUI -  A Processing UI library


PUI is a simple but maleable library for Processing 3.0+ aimed at supporting quick user interface design.  PUI runs on a basic synchornous flow and can be added to any processing sketch to introduce a new depth of control.
___

## Setting Up A PUI 
Adding a PUI to your Processing Sketch consits of three main steps:
 - Initializing PUI elements you desire in the `setup()` block
 - Registering PUI elements with a newly created `ObjectSelector`
 - Running the `ObjectSelector` inside of the `draw()` block
 
*Example*

The below code snippet shows how a Processing sketch can be setup to create a 500 by 500 pixel canvas on which a `HorizontalContinuousSlider` is drawn.

```
public void settings() {
    	size(500,500);
    }

public void setup() {
    	fill(120,50,240);
    	o = new ObjectSelector(this);
    	slider1 = new HorizontalContinuousSlider(20,200,100,4,this);
    	o.register(slider1);
    }

public void draw(){
    	o.run();
    } 
```
First, the size of the sketch and its background are set.  Then, a new `ObjectSelector` is created for `this` (the current PApplet).  Then, a slider is created as a `HorizontalContinuousSlider` with a location of (20,200) on the canvas, with a length of 100 and a button size of 4 pixels, and the `this` keyword is included again to show it is being drawn on the current PApplet.  Lastly, the `ObjectSelector` is run during the `draw()` block which listens for all PUI events and draws all elements.
___

## Current PUI Elements

PUI is still in its early developmental phase, and new elements are being designed and thought of every week while this project is active.  If you have any interest in contributing to this fun project please contribute your thoughtful ideas and help make PUI an even more user friendly and enjoyable expierence for all!

### Available Elements as of 1/20/2018

##### Sliders
There are four types of sliders.  `HorizontalContinuousSlider`, `HorizontalDiscreteSlider`, `VerticalContinuousSlider`, and `VerticalDiscreteSlider`.  

If a slider is *continuous*, this means the indicator (or button) on the slider can rest anywhere on the slider track.  If a slider is *discrete*, this means there are a user-defined amount of places on the slider track that the indicator can lie.  Intuitively, a *horizontal* slider goes from left to right, while a *discrete* slider goes up to down.  

Check the documentation in SLIDER_README.md to find out all functions, constructors, and usability.  

