package renderer;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;
import static java.awt.Color.RED;

class Level7Picture {

    private Scene scene = new Scene("Test scene");

    @Test
    public void Level7Picture() {
        Camera camera = new Camera(new Point(0, -25, 2200), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000).setAdaptiveSuperSampling(true).setMultiThreading(true);

      /*Camera camera1 = new Camera(new Point(0, -1500, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
              .setVPSize(2500, 2500).setVPDistance(10000); //  */

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),
                new Sphere(50d, new Point(-150, -150, -115)).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.7)),
                //
                new Triangle(new Point(-150, -150, -115), new Point(-130, 100, -170), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.05).setkS(0.5).setnShininess(400)), //
                new Sphere(70d, new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.002).setkS(0.8).setnShininess(30).setkT(0.003).setkR(0.9)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(-60, 50, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));

        ImageWriter imageWriter = new ImageWriter("Level7Picture", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }

}