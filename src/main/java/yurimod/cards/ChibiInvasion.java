package yurimod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import yurimod.actions.CorruptAction;
import yurimod.powers.InsanityPower;
import yurimod.yuriMod;
import yurimod.actions.InvasionAction;
import yurimod.patches.AbstractCardEnum;

public class ChibiInvasion
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Weirdness
 * Apply X (+1) keywords to yourself.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("ChibiInvasion");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_INVASION);

	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	
// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = -1;	
	private static final int DAMAGE = 5;
	private static final int UPGRADE_DAMAGE = 2;
	private static final int MAGIC = 2;
	private static final int UPGRADE_MAGIC = 1;

// /STAT DECLARATION/
	
	public ChibiInvasion() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseDamage = DAMAGE;
		this.isMultiDamage = true;
		this.baseMagicNumber = this.magicNumber = MAGIC;
		
	}
	
	@Override
	public void use(final AbstractPlayer p, final AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		AbstractDungeon.actionManager.addToBottom(new InvasionAction(p, this.multiDamage,
				this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "InsanityPower", this.magicNumber));
		if (AbstractDungeon.player.hasRelic("yuri:BloodyKnife") && !this.purgeOnUse) {
			AbstractDungeon.actionManager.addToBottom(new CorruptAction(this, new ChibiInvasionCorrupt()));
			FleetingField.fleeting.set(this, true);
		}
	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new ChibiInvasion();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE);
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.initializeDescription();
        }
    }
}