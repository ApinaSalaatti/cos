package com.dogshitempire.cos.cats.buffs;

import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.Skill;

/**
 *
 * @author Merioksan Mikko
 */
public class SkillBuff extends CatBuff {
    private final String skill;
    private final int amount;
    
    public SkillBuff(float length, String skill, int amount) {
        super(BuffType.TEMPORARY, length);
        
        this.skill = skill;
        this.amount = amount;
    }
    
    @Override
    public void onAdd(Cat cat) {
        super.onAdd(cat);
        
        Skill s = cat.getStats().getSkill(skill);
        if(s != null) {
            s.setBonus(amount);
        }
    }
    
    @Override
    public void onRemove() {
        super.onRemove();
        
        Skill s = cat.getStats().getSkill(skill);
        if(s != null) {
            s.setBonus(0);
        }
    }
}
