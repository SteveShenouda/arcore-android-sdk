#version 300 es

uniform mat4 u_MVPMatrix;       // A constant representing the combined model/view/projection matrix.

in vec4 a_Position;             // Per-vertex position information we will pass in.
in vec4 a_Color;                // Per-vertex color information we will pass in.

out vec4 v_Color;               // This will be passed into the fragment shader.

void main()                     // The entry point for our vertex shader.
{
   v_Color = a_Color;           // Pass the color through to the fragment shader.
                                // It will be interpolated across the line.
   gl_Position = u_MVPMatrix    // gl_Position is a special variable used to store the final position.
               * a_Position;    // Multiply the vertex by the matrix to get the final point in
}
