package yurimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.powers.WeakPower;
import yurimod.powers.GlitchedPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class BrokenCode
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * for each loop x2"
 * "Apply 1 Vulnerable to all enemies, 2(3) times.
 */
		
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("BrokenCode");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	
	// Image
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_BROKE_CODE);

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 1;
	private static final int MAGIC = 2;
	private static final int UPGRADE_MAGIC = 1;

// /STAT DECLARATION/
	
	public BrokenCode() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new GlitchedPower(mo, p, this.magicNumber), this.magicNumber));
		}
        	AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Glitch(), 2, false, true));
    }

    
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new BrokenCode();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.initializeDescription();
        }
    }
}