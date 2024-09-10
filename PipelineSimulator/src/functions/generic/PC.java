package functions.generic;

public class PC {
	
	static Integer pc = 0;   // PC começa em 0
	static Integer clock = 1; // Clock começa em 1

	public static Integer getClock() {
		return clock;
	}

	public static void setClock(Integer clock) {
		PC.clock = clock;
	}

	public static Integer getPc() {
		return pc;
	}

	public static void setPc(Integer pc) {
		PC.pc = pc;
	}

}

