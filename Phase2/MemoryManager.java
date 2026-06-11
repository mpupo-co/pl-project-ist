public class MemoryManager {

    public class VAddress {
        final int addr;
        VAddress(int rep) {
            addr = rep;
        }
        private int get()
        { return addr;}
    }

    final private int DEFAULT_SIZE = 10000;
    
    private IValue[] store;
    private int sp;
    private int limit;
    private final String panicstr = "PANIC: ";

    MemoryManager(int size)
    {
        limit = size;
        store = new IValue[limit];
        sp = limit;
    }

    MemoryManager()
    {
        limit = DEFAULT_SIZE;
        store = new IValue[limit];
        sp = limit;
        // invariant sp>=0 && sp <= limit
    }

    VAddress push(IValue v) throws MemoryPanicError {
        if (sp == 0)
            throw new MemoryPanicError(panicstr+"out of memory");
        sp = sp - 1;
        // sp >= 0 && sp < limit
        if (store[sp]!=null)
            throw new MemoryPanicError(panicstr+"push overwrite");
        store[sp] = v;
        return new VAddress(sp);
    }

    IValue pop() throws MemoryPanicError {
        if (sp==limit)
            throw new MemoryPanicError(panicstr+"read out of bounds");
        // sp < limit
        IValue v = store[sp];
        store[sp] = null;
        sp = sp + 1;
        // sp >= limit
        return v;
    }

    IValue memrd(VAddress address) throws MemoryPanicError{
        int addr = address.get();
        if (addr<sp || addr>=limit) {
            throw new MemoryPanicError(panicstr+"read out of bounds");
        }
        if (store[addr]==null)
            throw new MemoryPanicError(panicstr+"read empty location");
        return store[addr];
    }

    void memwrt(VAddress address, IValue v) throws MemoryPanicError{
        int addr = address.get();
        if (addr<sp || addr>=limit) {
            throw new MemoryPanicError(panicstr+ "write out of bounds");
        }
        store[addr]=v;
    }


}
