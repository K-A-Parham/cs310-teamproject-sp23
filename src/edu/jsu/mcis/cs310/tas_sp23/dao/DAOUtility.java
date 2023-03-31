package edu.jsu.mcis.cs310.tas_sp23.dao;

import java.util.ArrayList;
import java.util.HashMap;
import edu.jsu.mcis.cs310.tas_sp23.Punch;
import com.github.cliftonlabs.json_simple.*;

/**
 * 
 * Utility class for DAOs.  This is a final, non-constructable class containing
 * common DAO logic and other repeated and/or standardized code, refactored into
 * individual static methods.
 * 
 */
public final class DAOUtility {
    public static String getPunchListAsJSON(<ArrayListPunch>dailypunchlist) {
        ArrayList<Punch> jsonPunches = new ArrayList<>();
        return new JSONArray();
    } 
}