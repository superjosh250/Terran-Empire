package data.scripts;

import BORG.data.campaign.ids.BorgIDS;
import TERRAN.data.campaign.ids.TerranIDS;
import TERRAN.data.scripts.TerranOppositionForce;
import UFP.data.campaign.ids.DimensionsCrossedIDS;
import TERRAN.data.scripts.TerranSubjugationFleet;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import data.scripts.starsystems.Starbase4;
import data.scripts.starsystems.Terra;

public class TerranEmpireModPlugin extends BaseModPlugin {

    // Helper method to register the script without duplicates
    private void addSubjugationScript() {
        SectorAPI sector = Global.getSector();
        boolean hasScript = false;
        for (EveryFrameScript script : sector.getScripts()) {
            if (script instanceof TerranSubjugationFleet) {
                hasScript = true;
                break;
            }
        }

        if (!hasScript) {
            sector.addScript(new TerranSubjugationFleet());
            Global.getLogger(TerranEmpireModPlugin.class).info("TerranSubjugationFleet registered.");
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        super.onApplicationLoad();
        Global.getLogger(TerranEmpireModPlugin.class).info("Terran Empire mod loaded.");
    }

    @Override
    public void onNewGame() {
        SectorAPI sector = Global.getSector();

        // Generate systems/entities first
        new Terra().generate(sector);
        new Starbase4().generate(sector);

        // Faction relations
        FactionAPI TERRAN = sector.getFaction(TerranIDS.TERRAN);
        if (TERRAN == null) {
            Global.getLogger(TerranEmpireModPlugin.class).warn("TERRAN faction not found. Skipping faction relation setup.");
            addSubjugationScript();
            return;
        }

        FactionAPI UFP = sector.getFaction(DimensionsCrossedIDS.UFP);
        FactionAPI BORG = sector.getFaction(BorgIDS.BORG);
        FactionAPI player = sector.getFaction(Factions.PLAYER);
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI independent = sector.getFaction(Factions.INDEPENDENT);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI kol = sector.getFaction(Factions.KOL);
        FactionAPI diktat = sector.getFaction(Factions.DIKTAT);
        FactionAPI persean = sector.getFaction(Factions.PERSEAN);
        FactionAPI guard = sector.getFaction(Factions.LIONS_GUARD);

        // Safe relation assignments (skips if target faction is null/missing)
        setRelationSafe(TERRAN, player, 0.25f);
        setRelationSafe(TERRAN, UFP, -1f);
        setRelationSafe(TERRAN, BORG, -1f); // Added Borg relationship
        setRelationSafe(TERRAN, hegemony, -0.7f);
        setRelationSafe(TERRAN, tritachyon, -1f);
        setRelationSafe(TERRAN, pirates, -1f);
        setRelationSafe(TERRAN, independent, 0f);
        setRelationSafe(TERRAN, persean, -0.3f);
        setRelationSafe(TERRAN, church, -1f);
        setRelationSafe(TERRAN, path, -1f);
        setRelationSafe(TERRAN, kol, 0f);
        setRelationSafe(TERRAN, diktat, -0.5f);
        setRelationSafe(TERRAN, guard, -0.75f);

        // Register the script for the new game
        addSubjugationScript();

        Global.getLogger(TerranEmpireModPlugin.class).info("New game started for Terran Empire mod.");
    }

    @Override
    public void onGameLoad(boolean newGame) {
        if (Global.getSector() == null) return;

        // Register the script every time the game loads to ensure it's running
        addSubjugationScript();
        Global.getSector().addTransientScript(new TerranOppositionForce());

        Global.getLogger(TerranEmpireModPlugin.class).info("Game loaded for Terran Empire mod.");
    }

    // Helper method to safely apply relationships without causing NullPointerException
    private void setRelationSafe(FactionAPI primary, FactionAPI target, float relationship) {
        if (primary != null && target != null) {
            primary.setRelationship(target.getId(), relationship);
        }
    }
}