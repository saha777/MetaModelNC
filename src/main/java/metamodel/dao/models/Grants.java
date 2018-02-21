package metamodel.dao.models;

public class Grants {
    private Boolean r;
    private Boolean w;

    public Boolean getR() {
        return r;
    }

    public void setR(Boolean r) {
        this.r = r;
    }

    public Boolean getW() {
        return w;
    }

    public void setW(Boolean w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "Grants{" +
                "r=" + r +
                ", w=" + w +
                '}';
    }
}
