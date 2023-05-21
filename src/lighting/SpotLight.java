package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import java.util.*;
public class SpotLight extends PointLight implements LightSource{
    private Vector direction;
    private double narrowBeam = 1;

    public SpotLight setNarrowBeam(double narrow){
        this.narrowBeam = narrow;
        return this;
    }


    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }


    public Color getIntensity(Point p){
        Vector vector = getL(p);
        return super.getIntensity(p).scale(Math.pow(Math.max(0,this.direction.normalize().dotProduct(vector)), narrowBeam));
    }

}
