package com.google.ar.core.examples.java.common.samplerender.arcore;

import android.opengl.GLES30;

import com.google.ar.core.examples.java.common.samplerender.IndexBuffer;
import com.google.ar.core.examples.java.common.samplerender.Mesh;
import com.google.ar.core.examples.java.common.samplerender.SampleRender;
import com.google.ar.core.examples.java.common.samplerender.Shader;
import com.google.ar.core.examples.java.common.samplerender.VertexBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class LineRenderer {
    private int mMVPMatrixHandle;
    private int mPositionHandle;
    private int mColorHandle;

    static final int NUMBER_OF_BYTES_IN_FLOAT = 4;
    static final int COORDS_PER_VERTEX = 3;

    private static float[] lineCoords = {
            0.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f
    };

    // Set color with red, green, blue and alpha (opacity) values
    private static float color[] = {
            0.0f, 0.0f, 0.0f, 1.0f,
            .8f, .8f, 0f, 1.0f
    };

    private static final int vertexCount = lineCoords.length / COORDS_PER_VERTEX;
    //Specifies the byte offset between consecutive generic vertex attributes
    private static final int vertexStride = COORDS_PER_VERTEX * NUMBER_OF_BYTES_IN_FLOAT;
    // components_per_vertex * number_of_vertices * float_size
    private static final int COORDS_BUFFER_SIZE = COORDS_PER_VERTEX * vertexCount * NUMBER_OF_BYTES_IN_FLOAT;

    private static final FloatBuffer lineCoordsBuffer =
            ByteBuffer.allocateDirect(COORDS_BUFFER_SIZE).order(ByteOrder.nativeOrder()).asFloatBuffer();

    private static final FloatBuffer colorBuffer =
            ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();

    static {
        lineCoordsBuffer.put(lineCoords);
        lineCoordsBuffer.position(0);
        colorBuffer.put(color);
        colorBuffer.position(0);
    }

    public Shader lineShader;
    public Mesh mesh;

    /**
     * Allocates and initializes OpenGL resources needed by the background renderer. Must be called
     * during a {@link SampleRender.Renderer} callback, typically in {@link
     * SampleRender.Renderer#onSurfaceCreated()}.
     */
    public LineRenderer(SampleRender render) throws IOException {
        lineShader =
                Shader.createFromAssets(
                        render,
                        "shaders/line.vert",
                        "shaders/line.frag",
                        /*defines=*/ null)
                        .setDepthTest(false)
                        .setDepthWrite(false);

        // Set program handles. These will later be used to pass in values to the program.
        mMVPMatrixHandle = lineShader.getUniformLocation("u_MVPMatrix");
        mPositionHandle = lineShader.getAttribLocation("a_Position");
        mColorHandle = lineShader.getAttribLocation("a_Color");

        VertexBuffer[] vertexBuffers = {
                new VertexBuffer(render, 3, lineCoordsBuffer),
                new VertexBuffer(render, 4, colorBuffer),
        };
        mesh = new Mesh(render, Mesh.PrimitiveMode.LINES, null, vertexBuffers);
    }

    public void drawLine(SampleRender render) {
        render.draw(mesh, lineShader);
/*        render.draw(lineShader);

        // Pass in the position information
        GLES30.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES30.GL_FLOAT, false,
                vertexStride, lineCoordsBuffer);

        // Enable a handle to the vertices
        GLES30.glEnableVertexAttribArray(mPositionHandle);

        // Pass in the color information
        GLES30.glVertexAttribPointer(mColorHandle, 4, GLES30.GL_FLOAT, false,
                16, colorBuffer);

        GLES30.glEnableVertexAttribArray(mColorHandle);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);

        //Handled outside of this class
        //GLES30.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);

        //Draw the Line
        GLES30.glDrawArrays(GLES30.GL_LINES, 0, vertexCount);*/

/*        for(Line line : listOfArrays) {
            line.draw(gl);
        }
        isFirst = true;*/
    }

/*    public void setLineCoordinates(float mPreviousX
            , float mPreviousY, float x, float y) {
        // TODO Auto-generated method stub

        float lineCoords[] = new float[6];
        lineCoords[0] = (float) (mPreviousX * 2.0 / WIDTH - 1.0);
        lineCoords[1] = (float) (mPreviousY * -2.0 / HEIGHT + 1.0);
        lineCoords[2] = 0.0f;
        lineCoords[3] = (float) (x * 2.0 / WIDTH - 1.0);
        lineCoords[4] = (float) (y * -2.0 / HEIGHT + 1.0);
        lineCoords[5] = 0.0f;

        bufferOfArrays.add(new Line(lineCoords));

        if(isFirst) {
            isFirst = false;
            listOfArrays.addAll(bufferOfArrays);
            view.requestRender();
        }

    }

    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }*/
}
