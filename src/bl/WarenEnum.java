/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

/**
 *
 * @author Maxi
 */
public enum WarenEnum
{
    SORTE("sorte"),
    MENGE("menge"),
    GEWICHT("gewicht"),
    ABLAUFDATUM("ablaufdatum");
    
    private String name;

    private WarenEnum(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    
}
