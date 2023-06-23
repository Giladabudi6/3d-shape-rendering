package renderer;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;

class pyramid {
    private Scene scene = new Scene("Test scene");

    @Test
    public void finalPicturePyramid() {
        Camera camera = new Camera(new Point(-350, -390, 190), new Vector(0.9, 1, -0.430), (new Vector(0.9, 1, -0.430))
                .crossProduct(new Vector(-1, 1, 0))).setAntiAliasing(false)
                .setMultiThreading(true)
                .setAdaptiveSuperSampling(true).setRecursionDepth(5)
                .setVPSize(200, 200).setVPDistance(1000);

        Camera camera1 = new Camera(new Point(0, 0, 550), new Vector(0, 0, -1), (new Vector(0, 0, -1))
                .crossProduct(new Vector(1, 0, 0))).setAntiAliasing(false)
                .setMultiThreading(true)
                .setAdaptiveSuperSampling(false).setRecursionDepth(5)
                .setVPSize(200, 200).setVPDistance(1000);

        Camera camera2 = new Camera(new Point(270, 270, 260), new Vector(-1, -1, -1), (new Vector(-1, -1, -1))
                .crossProduct(new Vector(1, 0, 0))).setAntiAliasing(false)
                .setMultiThreading(true)
                .setAdaptiveSuperSampling(false).setRecursionDepth(5)
                .setVPSize(200, 200).setVPDistance(1000);



        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        // points for geometries:

        // points for ground :
        Point ground1 = new Point (-50, -50, 0);
        Point ground2 = new Point(50, -50, 0);
        Point ground3 = new Point(50, 50, 0);
        Point ground4 = new Point(-50, 50, 0);


        //points for Camel1:

        Point c1 = new Point(14, -16, 2);
        Point c2 = new Point(14, -17, 2);
        Point c3 = new Point(14, -17,0);
        Point c4 = new Point(14, -16, 0);
        Point c5 = new Point(14, -14, 2);
        Point c6 = new Point(14, -13, 0);
        Point c7 = new Point(14, -14, 0);
        Point c8 = new Point(14, -17, 3);
        Point c9 = new Point(14, -16, 4);
        Point c10 = new Point(14, -14, 4);
        Point c11 = new Point(14, -13, 2);
        Point c12 = new Point(14, -13, 3);
        Point c13 = new Point(14, -12, 4);
        Point c14 = new Point(14, -11, 3);
        Point c15 = new Point(14, -12, 3);
        Point c16 = new Point(14, -15, 4.5);
        Point c17 = new Point(14, -14, 3);


        //points for Camel2:
        Vector v1 = new Vector(7,-4,0);

        Point ca1 = new Point(14, -16, 2).add(v1);
        Point ca2 = new Point(14, -17, 2).add(v1);
        Point ca3 = new Point(14, -17,0).add(v1);
        Point ca4 = new Point(14, -16, 0).add(v1);
        Point ca5 = new Point(14, -14, 2).add(v1);
        Point ca6 = new Point(14, -13, 0).add(v1);
        Point ca7 = new Point(14, -14, 0).add(v1);
        Point ca8 = new Point(14, -17, 3).add(v1);
        Point ca9 = new Point(14, -16, 4).add(v1);
        Point ca10 = new Point(14, -14, 4).add(v1);
        Point ca11 = new Point(14, -13, 2).add(v1);
        Point ca12 = new Point(14, -13, 3).add(v1);
        Point ca13 = new Point(14, -12, 4).add(v1);
        Point ca14 = new Point(14, -11, 3).add(v1);
        Point ca15 = new Point(14, -12, 3).add(v1);
        Point ca16 = new Point(14, -15, 4.5).add(v1);
        Point ca17 = new Point(14, -14, 3).add(v1);


        //points for Camel3:
        Vector v2 = new Vector(20,-27,0);

        Point cb1 = new Point(-16,14, 2).add(v2);
        Point cb2 = new Point(-17,14, 2).add(v2);
        Point cb3 = new Point( -17,14,0).add(v2);
        Point cb4 = new Point( -16, 14,0).add(v2);
        Point cb5 = new Point( -14, 14,2).add(v2);
        Point cb6 = new Point( -13, 14,0).add(v2);
        Point cb7 = new Point( -14, 14,0).add(v2);
        Point cb8 = new Point( -17, 14,3).add(v2);
        Point cb9 = new Point( -16,14, 4).add(v2);
        Point cb10 = new Point( -14,14, 4).add(v2);
        Point cb11 = new Point( -13,14, 2).add(v2);
        Point cb12 = new Point( -13, 14,3).add(v2);
        Point cb13 = new Point( -12, 14,4).add(v2);
        Point cb14 = new Point( -11, 14,3).add(v2);
        Point cb15 = new Point( -12, 14,3).add(v2);
        Point cb16 = new Point( -15, 14,4.5).add(v2);
        Point cb17 = new Point( -14, 14,3).add(v2);


        //points for tree:
        Point t1 = new Point(0, 12, 0);
        Point t2 = new Point(0, 14, 0);
        Point t3 = new Point(0, 12, 2);
        Point t4 = new Point(0, 14, 2);
        Point t5 = new Point(0, 15, 2);
        Point t6 = new Point(0, 11, 2);
        Point t7 = new Point(0, 13, 6);

        //points for tree2:
        Vector v3 = new Vector(15,-15,0);

        Point ta1 = new Point(0, 10, 0).add(v3);
        Point ta2 = new Point(0, 12, 0).add(v3);
        Point ta3 = new Point(0, 10, 2).add(v3);
        Point ta4 = new Point(0, 12, 2).add(v3);
        Point ta5 = new Point(0, 13, 2).add(v3);
        Point ta6 = new Point(0, 9, 2).add(v3);
        Point ta7 = new Point(0, 11, 6).add(v3);

        //points for tree3:
        Vector v4 = new Vector(20,-14,0);

        Point tb1 = new Point(0, 10, 0).add(v4);
        Point tb2 = new Point(0, 12, 0).add(v4);
        Point tb3 = new Point(0, 10, 2).add(v4);
        Point tb4 = new Point(0, 12, 2).add(v4);
        Point tb5 = new Point(0, 13, 2).add(v4);
        Point tb6 = new Point(0, 9, 2).add(v4);
        Point tb7 = new Point(0, 11, 6).add(v4);



                scene.geometries.add( //

                // ground
                new Triangle(ground1, ground2, ground3).setEmission(new Color(200,180,100))
                        .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setnShininess(100)),

                new Triangle(ground1, ground3, ground4).setEmission(new Color(200,180,100))
                        .setMaterial(new Material().setkD(0.7).setkS(0.2).setkT(0.003).setnShininess(100)),

                //pyramid1:
                new Triangle(new Point (-10, -10, 0),new Point(0, 10, 0),new Point(0, 0, 13)).
                        setEmission(new Color(159,125,79)),
                new Triangle(new Point(10, -10, 0),new Point(-10, -10, 0),new Point(0, 0, 13)).
                        setEmission(new Color(149,115,69)),
                new Triangle(new Point(0, 10, 0), new Point(10, -10, 0), new Point(0, 0, 13)).
                        setEmission(new Color(139,105,59)),


                //pyramid2:
                new Triangle(new Point (-30, 10, 0),new Point(-20, 30, 0),new Point(-20, 20, 10)).
                        setEmission(new Color(159,125,79)),
                new Triangle(new Point(-10, 10, 0),new Point(-30, 10, 0),new Point(-20, 20, 10)).
                        setEmission(new Color(149,115,69)),
                new Triangle(new Point(-20, 30, 0), new Point(-10, 10, 0), new Point(-20, 20, 10)).
                        setEmission(new Color(139,105,59)),


                //tree1:
                new Triangle(t1,t2,t3).setEmission(new Color(59,15,20)),
                new Triangle(t2,t3,t4).setEmission(new Color(59,15,20)),
                new Triangle(t5,t6,t7).setEmission(new Color(0,125,0)),

                //tree2:
                new Triangle(ta1,ta2,ta3).setEmission(new Color(59,15,20)),
                new Triangle(ta2,ta3,ta4).setEmission(new Color(59,15,20)),
                new Triangle(ta5,ta6,ta7).setEmission(new Color(0,125,0)),

                //tree3:
                new Triangle(tb1,tb2,tb3).setEmission(new Color(59,15,20)),
                new Triangle(tb2,tb3,tb4).setEmission(new Color(59,15,20)),
                new Triangle(tb5,tb6,tb7).setEmission(new Color(0,125,0)),



                // Camel1:
                new Triangle(c1,c2,c4).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c2,c3,c4).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c1,c2,c8).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c1,c8,c9).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c9,c10,c16).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c1,c9,c10).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c1,c5,c10).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c10,c12,c17).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c5,c12,c17).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c5,c11,c12).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c5,c6,c7).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c5,c6,c11).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c11,c12,c15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c12,c13,c15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(c13,c14,c15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),


                // Camel2:
                new Triangle(ca1,ca2,ca4).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca2,ca3,ca4).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca1,ca2,ca8).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca1,ca8,ca9).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca9,ca10,ca16).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca1,ca9,ca10).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca1,ca5,ca10).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca10,ca12,ca17).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca5,ca12,ca17).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca5,ca11,ca12).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca5,ca6,ca7).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca5,ca6,ca11).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca11,ca12,ca15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca12,ca13,ca15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(ca13,ca14,ca15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),



                // Camel2:
                new Triangle(cb1,cb2,cb4).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb2,cb3,cb4).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb1,cb2,cb8).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb1,cb8,cb9).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb9,cb10,cb16).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb1,cb9,cb10).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb1,cb5,cb10).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb10,cb12,cb17).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb5,cb12,cb17).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb5,cb11,cb12).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb5,cb6,cb7).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb5,cb6,cb11).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb11,cb12,cb15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb12,cb13,cb15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),

                new Triangle(cb13,cb14,cb15).setEmission(new Color(59,15,20)) //
                        .setMaterial(new Material().setkD(0.7).setkS(0.7).setkT(0.003).setnShininess(100)),



                //sun:
                new Sphere(15, new Point(80,50,35)).setEmission(new Color(400,170,20)) //
                        .setMaterial(new Material().setkD(0.0007).setkS(1).setkT(0.003)));



        // light sources
        scene.lights.add(new SpotLight(new Color(770,740,100), new Point(38,17,23), new Vector(-2.1, -1, -1))
                .setNarrowBeam(5));


        ImageWriter imageWriter = new ImageWriter("finalPicturePyramid", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage();

        camera.writeToImage();
    }

}