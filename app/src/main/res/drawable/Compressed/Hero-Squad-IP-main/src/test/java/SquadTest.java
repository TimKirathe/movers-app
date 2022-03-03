import models.Hero;
import models.Squad;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class SquadTest {
    @Test
    public void instantiatesSquadCorrectly_true() {
        Hero hero = new Hero("Uhuru", 26, "trap", "Patience", "Drive");
        Squad squad = new Squad("Vikings", "Defeat spartans");
        assertEquals(true, squad instanceof Squad);
    }
    @Test
    public void succesFullyGetsHero_true() {
        Hero hero = new Hero("Uhuru", 26, "trap", "Patience", "Drive");
        Squad squad = new Squad("Vikings", "Defeat spartans");
        squad.getHeroes().add(hero);
        assertEquals(hero, squad.getHeroes().get(0));
    }
    @Test
    public void checksIfSquadCanAddHeroes_true() {
        Hero eric = new Hero("Eric", 26, "Super Coder", "Patience", "ForexTrader");
        Hero brian = new Hero("Brian", 27, "Pilot", "Never broke", "Wings");
        Squad avengers = new Squad("Avengers", "Defeat Spartans");
        avengers.setHeroes(eric);
        avengers.setHeroes(brian);
        assertEquals(true, avengers.getHeroes().contains(eric));
    }

    @Test
    public void checksIfHeroAppearsInOnlyOneSquadAtATime_true() {
        Hero eric = new Hero("Eric", 26, "Super Coder", "Patience", "ForexTrader");
        Hero brian = new Hero("Brian", 27, "Pilot", "Never broke", "Wings");

        Squad avengers = new Squad("Avengers", "Defeat Spartans");
        Squad justiceLeague = new Squad("Justice League", "Fight Crime");
        avengers.setHeroes(eric);
        justiceLeague.setHeroes(eric);
        avengers.setHeroes(brian);
        assertEquals(false, avengers.doesHeroExist(brian));
    }
    @Test
    public void getPublished_isFalseAfterInstantiation_false() {
        Squad avengers = new Squad("Avengers", "Defeat Spartans");
        assertEquals(false, avengers.getPublished());
    }
    @Test
    public void getId_squadInstantiatesWithAnID_1() throws Exception {
        Squad.clearAllPosts();
        Squad avengers = new Squad("Avengers", "Defeat Spartans");
        Squad justiceLeague = new Squad("Justice League", "Fight Crime");
        assertEquals(2, Squad.findById(justiceLeague.getId()).getId());
    }
    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Squad avengers = new Squad("Avengers", "Defeat Spartans");
        assertEquals(LocalDateTime.now().getDayOfWeek(), avengers.getCreatedAt().getDayOfWeek());
    }


}
