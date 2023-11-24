-- Views for easy access of tables

CREATE VIEW TransactionsDescendingDate AS
SELECT id, user_id, product_id, transaction_time, transaction_date
FROM Transaction
ORDER BY transaction_date DESC;