import models.Hero;
import models.Squad;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HeroTest {
//    check to see if an object is correctly created
    @Before
    public void tearDown(){
        Hero.clearAllHeroes();
    }
    @Test
    public void heroObjectIsCorrectlyCreated_true() {
        Hero superHero = new Hero("Brian", 26, "Coder", "Patience", "master");
        assertEquals(true, superHero instanceof Hero);
    }
    @Test
    public void allHeroesContainsAllHeroes_true() {
        Hero superHero = new Hero("Briam", 26, " Coder", "Patience", "master");
        Hero secondSuperHero = new Hero("Twitty", 21, "Super", "Patience", "DC");
        assertEquals(true, Hero.getAll().contains(superHero));
        assertEquals(true, Hero.getAll().contains(secondSuperHero));
    }
    @Test
    public void getId_heroInstantiatesWithAnID_1() throws Exception {
        Hero superHero = new Hero("Brian", 26, "Super Coder", "My Patience", "master");
        Hero secondSuperHero = new Hero("Trees", 21, "Punch", "Patience", "DC");
        assertEquals(2, Hero.findById(secondSuperHero.getId()).getId());
    }
    @Test
    public void getPublished_isFalseAfterInstantiation_false() {
        Hero eric = new Hero("Brian", 26, "Super Coder", "My Patience", "master");
        assertEquals(false, eric.getPublished());
    }
    @Test
    public void delete_deleteASpecificHero() throws Exception{
        Hero eric = setUpNewHero();
        Hero catherine= new Hero("Catherine", 20, "Super ", "Patience", "Dc");
        eric.deleteHero();
        assertEquals(1, Hero.getAll().size());
        assertEquals(Hero.getAll().get(0).getId(), 2);
    }
    @Test
    public void updateChangesHeroContent() throws Exception {
        Hero hero = setUpNewHero();

        int formerId = hero.getId();
        String formerName = hero.getName();
        int formerAge = hero.getAge();
        String formerSpecialPower = hero.getSpecialPower();
        String formerWeakness = hero.getWeakness();

        hero.setName("Wonder woman");
        hero.setAge(20);
        hero.setSpecialPower("Fly");
        hero.setWeakness("sleep");

        assertEquals(formerId, hero.getId());
        assertNotEquals(formerName, hero.getName());
        assertNotEquals(formerAge, hero.getAge());
        assertNotEquals(formerSpecialPower, hero.getSpecialPower());
        assertNotEquals(formerWeakness, hero.getWeakness());
    }
    @Test
    public void squad_correctlyInstantiatesSquadObject(){
        Hero eric = setUpNewHero();
        Squad avengers = setUpNewSquad();
        assertEquals(true, avengers instanceof Squad);
    }

    private Hero setUpNewHero(){
        return new Hero("Ruto", 26, "Super Coder", "My Patience", "Air bending");
    }

    private Squad setUpNewSquad(){
        return new Squad("Avengers", "Defeat Thanos");
    }


}
