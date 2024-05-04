package com.getposted.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("SkillDAOTest")
public class SkillDAOImplTest {

    private static Skill skill = new Skill();
    private static SkillDAOImpl skillDAOImpl = new SkillDAOImpl();

    @BeforeClass
    public static void createDatabase() {
        TestDataBase.createAll();
    }

    @Test
    public void testDelete() throws SQLException {
        skill.setSkill("testDelete");
        skill.setId(27);
        skillDAOImpl.insert(skill);

        assertNotNull(skillDAOImpl.get(27));

        skillDAOImpl.delete(skill);
        assertTrue(skillDAOImpl.get(27) == null);
    }

    @Test
    public void testGetInt() throws SQLException {
        skill = skillDAOImpl.get(1);
        assertEquals(skill.getSkill(), "Programming");
        skill = skillDAOImpl.get(8);
        assertEquals(skill.getSkill(), "Web Development");
    }

    @Test
    public void testGetString() throws SQLException {
        skill = skillDAOImpl.get("Programming");
        assertEquals(skill.getId(), 1);
        skill = skillDAOImpl.get("Data Analysis");
        assertEquals(skill.getId(), 2);
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Skill> skills = skillDAOImpl.getAll();
        assertTrue(skills.size() >= 10);
        for (Skill skill : skills) {
            assertTrue(skill.getSkill().length() >= 2);
        }
    }

    @Test
    public void testGetList() throws SQLException {
        List<Skill> skills = skillDAOImpl.getList(5);
        assertTrue(skills.size() == 5);
        for (Skill skill : skills) {
            assertTrue(skill.getSkill().length() >= 2);
        }
    }

    @Test
    public void testInsert() throws SQLException {
        skill.setSkill("testInsert");
        skill.setId(21);

        skillDAOImpl.insert(skill);

        assertNotNull(skillDAOImpl.get("testInsert"));
        assertNotNull(skillDAOImpl.get(21));

        assertEquals("testInsert", skillDAOImpl.get(21).getSkill());
    }

    @Test
    public void testUpdate() throws SQLException {
        skill.setSkill("testUpdate");
        skill.setId(22);
        skillDAOImpl.insert(skill);
        skill.setSkill("testUpdate2");

        skillDAOImpl.update(skill);

        assertEquals(skillDAOImpl.get(22).getSkill(), "testUpdate2");
    }

    @Test(expected = SQLException.class)
    public void testExceptionForInsert() throws SQLException {
        skill.setSkill("nano");
        skill.setId(40);
        skillDAOImpl.insert(skill);
        skillDAOImpl.insert(skill);
    }

    @AfterClass
    public static void deleteDatabase() {
        TestDataBase.deleteDatabase();
    }
}
