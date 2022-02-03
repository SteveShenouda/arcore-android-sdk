#version 300 es
#extension GL_OES_EGL_image_external_essl3 : require
precision mediump float;                // Set the default precision to medium. We don't need as high of a
                                        // precision in the fragment shader.
in vec4 v_Color;                        // This is the color from the vertex shader interpolated across the
                                        // line per fragment.
out vec4 FragColor;                  // This is the color being outputted

void main() {
   FragColor = v_Color;              // Pass the color directly through the pipeline.
}
