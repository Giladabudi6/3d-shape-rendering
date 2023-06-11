/**
 * 
 */
package renderer;

import static java.awt.Color.*;

import geometries.Plane;
import lighting.*;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
   private Scene scene = new Scene("Test scene");
   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheres() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(150, 150).setVPDistance(1000);

      scene.geometries.add( //
                           new Sphere(50d,new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
                           new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
      scene.lights.add( //
                       new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                          .setkL(0.0004).setkQ(0.0000006));

      camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage(); //
      camera.writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheresOnMirrors() {
      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(2500, 2500).setVPDistance(10000); //

      /*Camera camera1 = new Camera(new Point(0, -20000, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
              .setVPSize(2500, 2500).setVPDistance(10000); // */

      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

      scene.geometries.add( //
                           new Sphere( 400d,new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)
                                 .setkT(new Double3(0.5, 0, 0))),
                           new Sphere( 200d,new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(670, 670, 3000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setkR(1)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(-1500, -1500, -2000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));

      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
         .setkL(0.00001).setkQ(0.000005));

      ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage(); //
      camera.writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void trianglesTransparentSphere() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(200, 200).setVPDistance(1000);

      /*Camera camera1 = new Camera(new Point(0, -1500, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
              .setVPSize(2500, 2500).setVPDistance(10000); //  */

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.geometries.add( //
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
                           new Sphere( 30d, new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.6)));

      scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
         .setkL(4E-5).setkQ(2E-7));

      ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage(); //
      camera.writeToImage();
   }

   @Test
   public void picture1() {
      Camera camera = new Camera(new Point(0, -25, 2200), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(200, 200).setVPDistance(1000);

      /*Camera camera1 = new Camera(new Point(0, -1500, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
              .setVPSize(2500, 2500).setVPDistance(10000); //  */

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.geometries.add( //
              new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                      new Point(75, 75, -150)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),
              new Sphere(50d,new Point(-150, -150, -115)).setEmission(new Color(GREEN))
                      .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.7)),
                      //
              new Triangle(new Point(-150, -150, -115), new Point(-130, 100, -170), new Point(75, 75, -150)) //
                      .setMaterial(new Material().setkD(0.05).setkS(0.5).setnShininess(400)), //
              new Sphere( 70d, new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
                      .setMaterial(new Material().setkD(0.002).setkS(0.8).setnShininess(30).setkT(0.003).setkR(0.9)));

      scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
              .setkL(4E-5).setkQ(2E-7));
      scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(-60, 50, 0), new Vector(0, 0, -1)) //
              .setkL(4E-5).setkQ(2E-7));

      ImageWriter imageWriter = new ImageWriter("picture1", 600, 600);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage(); //
      camera.writeToImage();
   }

   @Test
   public void Bonus() {
      Camera camera = new Camera(new Point(-300, -300, 95), new Vector(1, 1, -0.2), (new Vector(1, 1, -0.2))
              .crossProduct(new Vector(-1,1,0))).setantiAliasing(true).setMT(true) //
              .setVPSize(200, 200).setVPDistance(1000);

      /*Camera camera1 = new Camera(new Point(0, -1500, 0), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
              .setVPSize(2500, 2500).setVPDistance(10000); //  */

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
      // points for geometries:

      // points for ground :
      Point ground1 = new Point(-25,-25,0);
      Point ground2 = new Point(25,-25,0);
      Point ground3 = new Point(25,25,0);
      Point ground4 = new Point(-25,25,0);

         // points for triangles (walls and roof):
      Point a = new Point(0,0,0);
      Point b = new Point(10,0,0);
      Point c = new Point(0,0,10);
      Point d = new Point(10,0,10);
      Point e = new Point(0,10,0);
      Point f = new Point(0,10,10);
      Point g = new Point(5,5,15);
      Point l = new Point(10,10,0);
      Point m = new Point(10,10,10);


      // points for clouds:
      Point h = new Point(-3,0,35);
      Point i = new Point(-14,0,34);
      Point j = new Point(-11,2,36);
      Point k = new Point(-7,-1,35);




      scene.geometries.add( //

              // ground
              new Triangle(ground1,ground2,ground4).setEmission(new Color(GREEN))
                      .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setnShininess(100)),

              new Triangle(ground4,ground2,ground3).setEmission(new Color(GREEN))
                      .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setnShininess(100)),

              // mirror
              new Triangle(new Point(25,0,90000),ground2,ground3).setEmission(new Color(30,30,30))
                      .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setkR(0.99).setnShininess(100)),

              new Triangle(new Point(0,25,90000),ground4,ground3).setEmission(new Color(50,50,50))
                      .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setkR(0.99).setnShininess(100)),

              // walls:
              new Triangle(a,b,c).setEmission(new Color(0,0,14)) //
                      .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),

              new Triangle(b,c,d).setEmission(new Color(0,0,14)) //
                      .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),

              new Triangle(a,c,e).setEmission(new Color(yellow)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

              new Triangle(c,e,f).setEmission(new Color(yellow)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

              new Triangle(b,d,l).setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

              new Triangle(d,m,l).setEmission(new Color(gray)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

              new Triangle(f,e,m).setEmission(new Color(150,20,70)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

              new Triangle(e,m,l).setEmission(new Color(150,20,70)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),


              // roof:
              new Triangle(f,c,g).setEmission(new Color(RED)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

              new Triangle(c,d,g).setEmission(new Color(RED)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

              new Triangle(d,m,g).setEmission(new Color(RED)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

              new Triangle(f,m,g).setEmission(new Color(RED)) //
                      .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),



              // clouds
              new Sphere(6d,h).setEmission(new Color(60,60,60))
                      .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.01)),

              new Sphere(5d,i).setEmission(new Color(60,60,60))
                      .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.01)),

              new Sphere(3d,j).setEmission(new Color(60,60,60))
                      .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.01)),

              new Sphere(7d,k).setEmission(new Color(60,60,60))
                      .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.01)));




      // light sources
      scene.lights.add(new DirectionalLight(new Color(94, 100, 86), new Vector(0, 0, -1)));
      scene.lights.add(new SpotLight(new Color(100, 30, 30),new Point(15,-5,0),new Vector(-1,1,0)));
      scene.lights.add(new PointLight(new Color(20, 45, 32),new Point(20,20,10)));


      ImageWriter imageWriter = new ImageWriter("Bonus", 600, 600);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage(); //
      camera.writeToImage();
   }
}
