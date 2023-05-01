package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Geometries implements Intersectable{
    private List<Intersectable> shapesList;

    public Geometries() {
        shapesList = new LinkedList<Intersectable>();
    }

    public Geometries (Intersectable...geometries) {
        this();
        add(geometries);
    }

    public void add (Intersectable...geometries) {
        for (Intersectable intersectable : geometries) {
            shapesList.add(intersectable);
        }
    }

    public List<Point> findIntersections(Ray ray) {
        // holds the intersection points
        LinkedList<Point> points=null;
        for(var geometry: shapesList){
            var geometryList=geometry.findIntersections(ray);
            if(geometryList!=null){
                if(points==null){
                    points=new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
}
