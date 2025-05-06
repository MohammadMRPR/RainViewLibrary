# RainView Library

RainView is a simple Android custom view that simulates rainfall using `SurfaceView`.

## Features
- Lightweight and easy to use
- Custom animation loop using threading
- Pure Java (no external dependencies)

## How to Use

1. Add the `rainlib.jar` to your Android project or Sketchware Pro.
2. Use the following code:


import com.rainlib.RainView;

RainView rain = new RainView(this);
addContentView(rain, new android.widget.RelativeLayout.LayoutParams(
    android.widget.RelativeLayout.LayoutParams.MATCH_PARENT,
    android.widget.RelativeLayout.LayoutParams.MATCH_PARENT
));
rain.resume();

## License
MIT License – see LICENSE file for details.
