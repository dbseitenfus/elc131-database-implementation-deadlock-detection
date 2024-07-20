package ibd.transaction.concurrency;

import ibd.transaction.Transaction;
import ibd.transaction.concurrency.danielseitenfus.Graph;
import ibd.transaction.instruction.Instruction;

public class DanielSeitenfusConcurrencyManager extends LockBasedConcurrencyManager{

    Graph graph;

    /**
     * @throws Exception
     */
    public DanielSeitenfusConcurrencyManager() throws Exception {
        this.graph = new Graph();
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
        Transaction result = super.addToQueue(item, instruction);

        Transaction transaction = instruction.getTransaction();
        if(transaction != null) {
            graph.addNode(transaction);
        }

        for(Lock lock : item.locks) {
            if(instruction.getMode() == Instruction.WRITE || instruction.getMode() == Instruction.UPDATE || instruction.getMode() == Instruction.DELETE) {
                if(lock.mode == Instruction.READ || lock.mode == Instruction.WRITE) {
                    graph.addEdge(instruction.getTransaction(), lock.transaction, item);
                }
            }else if(instruction.getMode() == Instruction.READ) {
                if(lock.mode == Instruction.WRITE || lock.mode == Instruction.UPDATE) {
                    graph.addEdge(instruction.getTransaction(), lock.transaction, item);
                }
            }
        }

        if(graph.detectCycle()) {
            result = graph.getNodeWithHighestIdInCycle().getTransaction();
            graph.removeNode(result);
        }

        return result;
    }
}
