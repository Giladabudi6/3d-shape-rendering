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


        if (shapesList.get(0).findIntersections(ray) == null ||
                shapesList.get(1).findIntersections(ray) == null ||
                shapesList.get(2).findIntersections(ray) == null)
            return null;


        List<Point> myList = new LinkedList<>();

        myList.addAll((shapesList.get(0).findIntersections(ray)));
        myList.addAll((shapesList.get(1).findIntersections(ray)));
        myList.addAll((shapesList.get(2).findIntersections(ray)));

        return myList;

    }
}
