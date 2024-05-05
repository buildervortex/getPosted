package com.getposted.model;

public class Has {
    private int authorId=0;
    private int skillId=0;

    public Has(){}

    public Has(int authorId, int skillId) {
        this.authorId = authorId;
        this.skillId = skillId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public String toString() {
        return "Has [authorId=" + authorId + ", skillId=" + skillId + "]";
    }

    
}
