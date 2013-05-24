package jp.mixi.practice.test.target;

/**
 * TODO: TestPractice2 の各テストケースをパスするメソッドを書く
 */
public class TestTarget2 {
	private final int LENGTH_VALIDATOR_MIN = 1;
	private final int LENGTH_VALIDATOR_MAX = 10;
    public boolean isValidLength(String string) {
    	boolean isValidLength = false;
    	int length = string.length();
    	if ( length >= LENGTH_VALIDATOR_MIN && length <= LENGTH_VALIDATOR_MAX) {
    		isValidLength = true;
    	}
        return isValidLength;
    }

    public String formatTextCount(int count, int max) {
        return Integer.toString(count) + " / " + Integer.toString(max);
    }
}