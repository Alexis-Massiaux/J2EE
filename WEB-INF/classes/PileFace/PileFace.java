package PileFace;

import java.util.*;

public class PileFace{

    private ArrayList<Character> humain;
    private ArrayList<Character> ordi;
    private int pointsHumain;
    private int pointsOrdi;
    public int numPartie;

    public PileFace(){
	humain=new ArrayList<Character>();
	ordi=new ArrayList<Character>();
	pointsHumain=0;
	pointsOrdi=0;
	numPartie=0;
    }

    public boolean termine(){
	return pointsHumain == 10 || pointsOrdi == 10;
    }

    public int getPointsOrdi(){
	return pointsOrdi;
    }

    public int getPointsHumain(){
	return pointsHumain;
    }

    public char getLastHumain(){
	return humain.get(humain.size()-1).charValue();
    }

    public char getLastOrdi(){
	return ordi.get(ordi.size()-1).charValue();
    }

    public void play(char h){
	char o;
	if(Math.random()>0.5)
	    o='P';
	else
	    o='F';
	humain.add(new Character(h));
	ordi.add(new Character(o));

	if(h==0)
	    pointsOrdi++;
	else
	    pointsHumain++;
	numPartie++;
    }
}
