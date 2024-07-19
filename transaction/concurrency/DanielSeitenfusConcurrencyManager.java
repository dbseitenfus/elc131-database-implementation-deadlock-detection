package ibd.transaction.concurrency;

import ibd.transaction.Transaction;
import ibd.transaction.instruction.Instruction;

public class DanielSeitenfusConcurrencyManager extends LockBasedConcurrencyManager{

    /**
     * @throws Exception
     */
    public DanielSeitenfusConcurrencyManager() throws Exception {

    }

    @Override
    public boolean commit(Transaction t) throws Exception {
        return super.commit(t);
    }

    @Override
    protected void abort(Transaction t) throws Exception {
        super.abort(t);
    }

    @Override
    protected Transaction addToQueue(Item item, Instruction instruction) {
        return super.addToQueue(item, instruction);
    }
}
