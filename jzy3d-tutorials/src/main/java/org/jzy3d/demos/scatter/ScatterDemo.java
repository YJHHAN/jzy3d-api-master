package org.jzy3d.demos.scatter;

import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;


public class ScatterDemo extends AbstractAnalysis{
    public static void main(String[] args) throws Exception {
        AnalysisLauncher.open(new ScatterDemo());
    }

    @ Override
    public void init() throws IOException {
        int size;
        float x;
        float y;
        float z;
        //float a;
        int a=7;

        this. reader();
        size = datasize;
        int typeNum;

        Coord3d[] points = new Coord3d[size];
        Color[]   colors = new Color[size];

        //Random r = new Random();
        //r.setSeed(0);

        //x = r.nextFloat() - 0.5f;
        //y = r.nextFloat() - 0.5f;
        //z = r.nextFloat() - 0.5f;

        for(int i=0; i<size; i++){

            x= value[i][0];
            y= value[i][1];
            z= value[i][2];

            typeNum=type[i];

            points[i] = new Coord3d(x, y, z);
            colors[i] = new Color(typeNum);
        }

        Scatter scatter = new Scatter(points, colors,a);
        chart = AWTChartComponentFactory.chart(Quality.Advanced, "newt");
        chart.getScene().add(scatter);

        System.out.println("spring = red");
        System.out.println("summer = blue");
        System.out.println("fall = green");
        System.out.println("winter = yellow");

    }

    float[] hsb;

    private float[] HSB(int r, int g, int b) {
        hsb = java.awt.Color.RGBtoHSB(r, g, b, null);
        return hsb;
    }


    float[][] value;
    int datasize=0;
    int[] type;

    void reader() throws IOException {
        String csvFile = "C:/Users/YJ/Desktop/Color/src/main/resources/hsb_jsy_4.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFile));

        String[] line;
        int[] rgb = new int[4];
        float[] get = new float[3];
        int index=0;

        while ((line = reader.readNext()) != null) {
            datasize++;
        }

        CSVReader reader2 = new CSVReader(new FileReader(csvFile));

        value = new float[datasize][3];
        type = new int[datasize];

        while ((line = reader2.readNext()) != null) {
            type[index] = Integer.parseInt(line[0]);
            value[index][0] = Float.parseFloat(line[1]);
            value[index][1] = Float.parseFloat(line[2]);
            value[index][2] = Float.parseFloat(line[3]);
            index++;
        }

    }

}
