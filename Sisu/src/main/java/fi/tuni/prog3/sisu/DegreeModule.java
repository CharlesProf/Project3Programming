package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.List;

public abstract class DegreeModule {
    private String name;
    private String id;
    private String groupId;
    private int minCredits;

    private String Code;
    private List<String> moduleGroupIds; // Updated field to store multiple moduleGroupIds

    private List<StudyModule> studyModules;

    public DegreeModule(String name, String id, String groupId, int minCredits, String Code){
        this.name = name;
        this.id = id;
        this.groupId = groupId;
        this.minCredits = minCredits;
        this.Code=Code;
        this.moduleGroupIds = new ArrayList<>();
        this.studyModules = new ArrayList<>();
    }



    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public int getMinCredits() {
        return this.minCredits;
    }

    public List<String> getModuleGroupIds() {
        return this.moduleGroupIds;
    }

    public void addModuleGroupId(String moduleGroupId) {
        this.moduleGroupIds.add(moduleGroupId);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setMinCredits(int minCredits) {
        this.minCredits = minCredits;
    }

    public void setModuleGroupIds(List<String> moduleGroupIds) {
        this.moduleGroupIds = moduleGroupIds;
    }

    public List<StudyModule> getStudyModules() {
        return studyModules;
    }

    public void setStudyModules(List<StudyModule> studyModules) {
        this.studyModules = studyModules;
    }

    @Override
    public String toString() {
        return "\nDegreeModule{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", minCredits=" + minCredits +
                ", Code='" + Code + '\'' +
                ", moduleGroupIds=" + moduleGroupIds +
                ", studyModules=" + studyModules +
                '}';
    }
}
