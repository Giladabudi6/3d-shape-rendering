package primitives;

public class Material {
    public Double3 kD = new Double3(0) ,kS = new Double3(0);
    public int nShininess = 0  ;

    // kT Represents the level of transparency/Refraction
    public Double3 kT = Double3.ZERO;
    // kR Represents the level of reflection
    public Double3 kR = Double3.ZERO;

    // kT setters:
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }


    // kR setters
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }


    // kD setters:
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }


    //kS setters:
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    // shininess setter
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
