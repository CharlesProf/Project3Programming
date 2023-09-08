package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.List;

public class StudyModule{
    private String name;
    private List<String> moduleGroupIds; // Updated field to store multiple moduleGroupIds
    private List<CourseModule> courseModules;
    public StudyModule(String name, List<String> moduleGroupIds) {
        this.name = name;
        this.moduleGroupIds = moduleGroupIds;
        this.courseModules= new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getModuleGroupIds() {
        return moduleGroupIds;
    }

    public void setModuleGroupIds(List<String> moduleGroupIds) {
        this.moduleGroupIds = moduleGroupIds;
    }

    public List<CourseModule> getCourseModules() {
        return courseModules;
    }

    public void setCourseModules(List<CourseModule> courseModules) {
        this.courseModules = courseModules;
    }

    @Override
    public String toString() {
        return "StudyModule{" +
                "name='" + name + '\'' +
                ", moduleGroupIds=" + moduleGroupIds +
                ", courseModules=" + courseModules +
                '}';
    }
}
