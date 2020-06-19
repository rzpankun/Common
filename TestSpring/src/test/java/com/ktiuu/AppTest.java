package com.ktiuu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.core.SimpleAliasRegistry;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        SimpleAliasRegistry simpleAliasRegistry = new SimpleAliasRegistry();
        simpleAliasRegistry.registerAlias("name1", "alias1");
        simpleAliasRegistry.registerAlias("name2", "alias2");
        simpleAliasRegistry.registerAlias("name3", "alias3");
        simpleAliasRegistry.registerAlias("name4", "alias4");
        simpleAliasRegistry.registerAlias("name5", "name1");
        simpleAliasRegistry.registerAlias("alias1", "alias5");

        System.out.println(simpleAliasRegistry.canonicalName("alias1"));
        System.out.println(simpleAliasRegistry.isAlias("alias1"));
        System.out.println(simpleAliasRegistry.getAliases("name1").length);
    }
}
