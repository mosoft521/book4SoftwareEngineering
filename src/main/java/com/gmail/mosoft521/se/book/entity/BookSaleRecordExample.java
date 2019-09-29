package com.gmail.mosoft521.se.book.entity;

import java.util.ArrayList;
import java.util.List;

public class BookSaleRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookSaleRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("BOOK_ID is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("BOOK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Integer value) {
            addCriterion("BOOK_ID =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Integer value) {
            addCriterion("BOOK_ID <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Integer value) {
            addCriterion("BOOK_ID >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BOOK_ID >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Integer value) {
            addCriterion("BOOK_ID <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Integer value) {
            addCriterion("BOOK_ID <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Integer> values) {
            addCriterion("BOOK_ID in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Integer> values) {
            addCriterion("BOOK_ID not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Integer value1, Integer value2) {
            addCriterion("BOOK_ID between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BOOK_ID not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdIsNull() {
            addCriterion("SALE_RECORD_ID is null");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdIsNotNull() {
            addCriterion("SALE_RECORD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdEqualTo(Integer value) {
            addCriterion("SALE_RECORD_ID =", value, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdNotEqualTo(Integer value) {
            addCriterion("SALE_RECORD_ID <>", value, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdGreaterThan(Integer value) {
            addCriterion("SALE_RECORD_ID >", value, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SALE_RECORD_ID >=", value, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdLessThan(Integer value) {
            addCriterion("SALE_RECORD_ID <", value, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("SALE_RECORD_ID <=", value, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdIn(List<Integer> values) {
            addCriterion("SALE_RECORD_ID in", values, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdNotIn(List<Integer> values) {
            addCriterion("SALE_RECORD_ID not in", values, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("SALE_RECORD_ID between", value1, value2, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andSaleRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SALE_RECORD_ID not between", value1, value2, "saleRecordId");
            return (Criteria) this;
        }

        public Criteria andTradeSumIsNull() {
            addCriterion("TRADE_SUM is null");
            return (Criteria) this;
        }

        public Criteria andTradeSumIsNotNull() {
            addCriterion("TRADE_SUM is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSumEqualTo(Integer value) {
            addCriterion("TRADE_SUM =", value, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumNotEqualTo(Integer value) {
            addCriterion("TRADE_SUM <>", value, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumGreaterThan(Integer value) {
            addCriterion("TRADE_SUM >", value, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("TRADE_SUM >=", value, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumLessThan(Integer value) {
            addCriterion("TRADE_SUM <", value, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumLessThanOrEqualTo(Integer value) {
            addCriterion("TRADE_SUM <=", value, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumIn(List<Integer> values) {
            addCriterion("TRADE_SUM in", values, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumNotIn(List<Integer> values) {
            addCriterion("TRADE_SUM not in", values, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumBetween(Integer value1, Integer value2) {
            addCriterion("TRADE_SUM between", value1, value2, "tradeSum");
            return (Criteria) this;
        }

        public Criteria andTradeSumNotBetween(Integer value1, Integer value2) {
            addCriterion("TRADE_SUM not between", value1, value2, "tradeSum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}