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

class house {

    private Scene scene = new Scene("Test scene");

    @Test
    public void finalPictureHouse() {
        Camera camera = new Camera(new Point(-300, -300, 95), new Vector(1, 1, -0.2), (new Vector(1, 1, -0.2))
                .crossProduct(new Vector(-1, 1, 0))).setAntiAliasing(false)
                .setMultiThreading(true)
                .setAdaptiveSuperSampling(true).setRecursionDepth(5) //
                .setVPSize(200, 200).setVPDistance(1000);


        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        // points for geometries:

        // points for ground :
        Point ground1 = new Point(-25, -25, 0);
        Point ground2 = new Point(25, -25, 0);
        Point ground3 = new Point(25, 25, 0);
        Point ground4 = new Point(-25, 25, 0);

        // points for triangles (walls and roof):
        Point a = new Point(0, 0, 0);
        Point b = new Point(10, 0, 0);
        Point c = new Point(0, 0, 10);
        Point d = new Point(10, 0, 10);
        Point e = new Point(0, 10, 0);
        Point f = new Point(0, 10, 10);
        Point g = new Point(5, 5, 15);
        Point l = new Point(10, 10, 0);
        Point m = new Point(10, 10, 10);
        Point n = new Point(0, 5, 10);
        Point o = new Point(0, 5, 5);
        Point p = new Point(0, 0, 5);


        // points for clouds:
        Point h = new Point(-3, 0, 35);
        Point i = new Point(-14, 0, 34);
        Point j = new Point(-11, 2, 36);
        Point k = new Point(-7, -1, 35);


        // points for man1:
        //LEFT LEG
        Point E = new Point(0, -8, 0);
        Point F = new Point(0, -9, 2);
        Point G = new Point(0, -8, 2);
        Point D = new Point(0, -9, 0);

        //RIGHT LEG
        Point A = new Point(0, -10, 0);
        Point B = new Point(0, -11, 0);
        Point C = new Point(0, -10, 2);
        Point H = new Point(0, -11, 2);


        //STOMACH
        Point I = new Point(0, -8, 5);
        Point J = new Point(0, -11, 5);

        //LEFT ARM
        Point K = new Point(0, -6, 3);
        Point L = new Point(0, -8, 4);

        //RIGHT ARM
        Point M = new Point(0, -11, 4);
        Point N = new Point(0, -13, 3);

        //HEAD
        Point O = new Point(0, -9.5, 6);
        int headRadius = 1;


        Vector man2 = new Vector(0, 24, 0);
        // points for man2:

        //LEFT LEG
        Point E2 = E.add(man2);
        Point F2 = F.add(man2);
        Point G2 = G.add(man2);
        Point D2 = D.add(man2);

        //RIGHT LEG
        Point A2 = A.add(man2);
        Point B2 = B.add(man2);
        Point C2 = C.add(man2);
        Point H2 = H.add(man2);

        //STOMACH
        Point I2 = I.add(man2);
        Point J2 = J.add(man2);

        //LEFT ARM
        Point K2 = K.add(man2);
        Point L2 = L.add(man2);

        //RIGHT ARM
        Point M2 = M.add(man2);
        Point N2 = N.add(man2);

        //HEAD
        Point O2 = O.add(man2);


        Vector man3 = new Vector(2, 14, 1);
        // points for man3:

        //LEFT LEG
        Point E3 = E.add(man3);
        Point F3 = F.add(man3);
        Point G3 = G.add(man3);
        Point D3 = D.add(man3);

        //RIGHT LEG
        Point A3 = A.add(man3);
        Point B3 = B.add(man3);
        Point C3 = C.add(man3);
        Point H3 = H.add(man3);

        //STOMACH
        Point I3 = I.add(man3);
        Point J3 = J.add(man3);

        //LEFT ARM
        Point K3 = K.add(man3);
        Point L3 = L.add(man3);

        //RIGHT ARM
        Point M3 = M.add(man3);
        Point N3 = N.add(man3);

        //HEAD
        Point O3 = O.add(man3);


        scene.geometries.add( //

                // ground
                new Triangle(ground1, ground2, ground4).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setnShininess(100)),

                new Triangle(ground4, ground2, ground3).setEmission(new Color(GREEN))
                        .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setnShininess(100)),

                // mirror
                new Triangle(new Point(25, 0, 90000), ground2, ground3).setEmission(new Color(30, 30, 30))
                        .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setkR(0.99).setnShininess(100)),

                new Triangle(new Point(0, 25, 90000), ground4, ground3).setEmission(new Color(50, 50, 50))
                        .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setkR(0.99).setnShininess(100)),

                // walls:
                new Triangle(a, b, c).setEmission(new Color(0, 0, 14)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),

                new Triangle(b, c, d).setEmission(new Color(0, 0, 14)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),

                new Triangle(b, d, l).setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(d, m, l).setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(f, e, m).setEmission(new Color(150, 20, 70)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(e, m, l).setEmission(new Color(150, 20, 70)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(a, f, e).setEmission(new Color(yellow)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(o, n, f).setEmission(new Color(yellow)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(a, o, p).setEmission(new Color(yellow)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),


                // window:
                new Triangle(n, o, c).setEmission(new Color(0, 0, 245)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.9).setnShininess(100)),

                new Triangle(c, o, p).setEmission(new Color(0, 0, 245)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.9).setnShininess(100)),


                // roof:
                new Triangle(f, c, g).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c, d, g).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(d, m, g).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(f, m, g).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),


                // clouds
                new Sphere(6d, h).setEmission(new Color(60, 60, 60))
                        .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.01)),

                new Sphere(5d, i).setEmission(new Color(60, 60, 60))
                        .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.01)),

                new Sphere(3d, j).setEmission(new Color(60, 60, 60))
                        .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.01)),

                new Sphere(7d, k).setEmission(new Color(60, 60, 60))
                        .setMaterial(new Material().setkD(0.01).setkS(0.05).setnShininess(30).setkT(0.01)),


                // man1:
                //RIGHT LEG
                new Triangle(A, B, C).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(B, C, H).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //LEFT LEG
                new Triangle(D, E, F).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(E, F, G).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //STOMACH
                new Triangle(G, H, I).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(H, J, I).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //RIGHT ARM
                new Triangle(J, M, N).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //LEFT ARM
                new Triangle(L, I, K).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //HEAD
                new Sphere(headRadius, O).setEmission(new Color(90, 0, 50)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),

                // man2:
                //RIGHT LEG
                new Triangle(A2, B2, C2).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(B2, C2, H2).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //LEFT LEG
                new Triangle(D2, E2, F2).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(E2, F2, G2).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //STOMACH
                new Triangle(G2, H2, I2).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(H2, J2, I2).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //RIGHT ARM
                new Triangle(J2, M2, N2).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //LEFT ARM
                new Triangle(L2, I2, K2).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //HEAD
                new Sphere(headRadius, O2).setEmission(new Color(90, 0, 50)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),


                // man3:
                //RIGHT LEG
                new Triangle(A3, B3, C3).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(B3, C3, H3).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //LEFT LEG
                new Triangle(D3, E3, F3).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(E3, F3, G3).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //STOMACH
                new Triangle(G3, H3, I3).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                new Triangle(H3, J3, I3).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //RIGHT ARM
                new Triangle(J3, M3, N3).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //LEFT ARM
                new Triangle(L3, I3, K3).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)),
                //HEAD
                new Sphere(headRadius, O3).setEmission(new Color(90, 0, 50)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003).setnShininess(20)));

        // light sources
        scene.lights.add(new DirectionalLight(new Color(94, 100, 86), new Vector(0, 0, -1)));
        //scene.lights.add(new SpotLight(new Color(100, 30, 30), new Point(15, -5, 0), new Vector(-1, 1, 0)));
        scene.lights.add(new PointLight(new Color(150, 150, 0), new Point(5, 5, 14)));
        scene.lights.add(new SpotLight(new Color(70, 110, 85), new Point(10, 14.5, 12), new Vector(-1, 0, -0.5)));


        ImageWriter imageWriter = new ImageWriter("finalPictureHouse", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage();

        camera.writeToImage();
    }

}