package fi.tuni.prog3.sisu;

public class CourseModule {
    private String name;
    private int minCredits;
    private int maxCredits;

    private String code;


    public CourseModule(String name, int minCredits, int maxCredits, String code) {
        this.name = name;
        this.minCredits = minCredits;
        this.maxCredits = maxCredits;
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinCredits() {
        return minCredits;
    }

    public void setMinCredits(int minCredits) {
        this.minCredits = minCredits;
    }

    public int getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(int maxCredits) {
        this.maxCredits = maxCredits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "CourseModule{" +
                "name='" + name + '\'' +
                ", minCredits=" + minCredits +
                ", maxCredits=" + maxCredits +
                ", code='" + code + '\'' +
                '}';
    }
}
