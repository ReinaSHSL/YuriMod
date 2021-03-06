package yurimod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import yurimod.powers.InsanityPower;
import yurimod.yuriMod;

public class GlassOfWine extends CustomRelic
{
	// ID, images, stats.
	public static final String ID = yurimod.yuriMod.makeID("GlassOfWine");
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_WINE);
	public static final String OUTLINE = yuriMod.makePath(yuriMod.yuri_WINE_OUTLINE);

	public GlassOfWine()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.SHOP, LandingSound.CLINK);
    }

    // Description
	@Override
	public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
	}

	//
    @Override
    public void atBattleStart(){
        this.flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InsanityPower(AbstractDungeon.player, AbstractDungeon.player, 3), 3));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy()
    {
        return new GlassOfWine();
    }
}
