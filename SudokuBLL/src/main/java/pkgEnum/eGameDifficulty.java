package pkgEnum;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum eGameDifficulty {

	EASY(0.10,5), MEDIUM(0.25,4), HARD(0.50,3);

	private final Double dDifficulty;
	private final int mMistakes;

	private static final Map<Double, eGameDifficulty> lookup = new HashMap<Double, eGameDifficulty>();

	static {
		for (eGameDifficulty d : eGameDifficulty.values()) {
			lookup.put(d.getdDifficulty(), d);
		}
	}

	private eGameDifficulty(Double dDifficulty, int mMistakes) {
		this.dDifficulty = dDifficulty;
		this.mMistakes = mMistakes;
	}

	public Double getdDifficulty() {
		return dDifficulty;
	}
	
	public int getmMistakes() {
		return mMistakes;
	}
	
	@Override
	public String toString() {
		return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
	}

	/**
	 * get - return the right enum by name
	 * 
	 * @param strName
	 * @return
	 */
	public static eGameDifficulty get(String strName) {
		for (eGameDifficulty eGD : eGameDifficulty.values()) {
			System.out.println(eGD.name().toUpperCase());
			if (eGD.name().toUpperCase().equals(strName.toUpperCase())) {
				return eGD;
			}
		}
		return null;
	}

	/**
	 * get - return the right enum by iDifficulty score.
	 * 
	 * @param strName
	 * @return
	 */

	public static eGameDifficulty get(int iDifficulty) {

		Iterator it = lookup.entrySet().iterator();
		eGameDifficulty eGD = null;

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			eGameDifficulty enumDifficulty = (eGameDifficulty) pair.getValue();
			int iDifficultyValue = (int) pair.getKey();
			if (iDifficulty > iDifficultyValue) {
				eGD = enumDifficulty;
			}
		}
		return eGD;
	}
}
