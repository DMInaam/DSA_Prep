class BankOp {
    private long[] accounts;
    private int n;

    public BankOp(long[] balance) {
        this.accounts = balance;
        this.n = balance.length; 
    }
    private boolean isValid(int account) {
        return account >= 1 && account <= n;
    }
    
    public boolean transfer(int account1, int account2, long money) {
        if (!isValid(account1) || !isValid(account2)) {
            return false;
        }
        if (accounts[account1 - 1] < money) {
            return false;
        }
        accounts[account1 - 1] -= money;
        accounts[account2 - 1] += money;
        return true;
    }
    
    public boolean deposit(int account, long money) {
        if (!isValid(account)) {
            return false;
        }
        accounts[account - 1] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        if (!isValid(account)) {
            return false;
        }
        if (accounts[account - 1] < money) {
            return false;
        }
        accounts[account - 1] -= money;
        return true;
    }
    
}