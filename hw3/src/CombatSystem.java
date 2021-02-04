
public interface CombatSystem {
    
    void engage ( Empty defender);
    void engage ( Wall defender);
    void engage (Enemy defender);
    void engage ( Player defender);

}
