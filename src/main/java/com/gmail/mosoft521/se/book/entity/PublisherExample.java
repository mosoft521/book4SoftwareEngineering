package com.gmail.mosoft521.se.book.entity;

import java.util.ArrayList;
import java.util.List;

public class PublisherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PublisherExample() {
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

        public Criteria andPubNameIsNull() {
            addCriterion("PUB_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPubNameIsNotNull() {
            addCriterion("PUB_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPubNameEqualTo(String value) {
            addCriterion("PUB_NAME =", value, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameNotEqualTo(String value) {
            addCriterion("PUB_NAME <>", value, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameGreaterThan(String value) {
            addCriterion("PUB_NAME >", value, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameGreaterThanOrEqualTo(String value) {
            addCriterion("PUB_NAME >=", value, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameLessThan(String value) {
            addCriterion("PUB_NAME <", value, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameLessThanOrEqualTo(String value) {
            addCriterion("PUB_NAME <=", value, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameLike(String value) {
            addCriterion("PUB_NAME like", value, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameNotLike(String value) {
            addCriterion("PUB_NAME not like", value, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameIn(List<String> values) {
            addCriterion("PUB_NAME in", values, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameNotIn(List<String> values) {
            addCriterion("PUB_NAME not in", values, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameBetween(String value1, String value2) {
            addCriterion("PUB_NAME between", value1, value2, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubNameNotBetween(String value1, String value2) {
            addCriterion("PUB_NAME not between", value1, value2, "pubName");
            return (Criteria) this;
        }

        public Criteria andPubTelIsNull() {
            addCriterion("PUB_TEL is null");
            return (Criteria) this;
        }

        public Criteria andPubTelIsNotNull() {
            addCriterion("PUB_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andPubTelEqualTo(String value) {
            addCriterion("PUB_TEL =", value, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelNotEqualTo(String value) {
            addCriterion("PUB_TEL <>", value, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelGreaterThan(String value) {
            addCriterion("PUB_TEL >", value, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelGreaterThanOrEqualTo(String value) {
            addCriterion("PUB_TEL >=", value, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelLessThan(String value) {
            addCriterion("PUB_TEL <", value, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelLessThanOrEqualTo(String value) {
            addCriterion("PUB_TEL <=", value, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelLike(String value) {
            addCriterion("PUB_TEL like", value, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelNotLike(String value) {
            addCriterion("PUB_TEL not like", value, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelIn(List<String> values) {
            addCriterion("PUB_TEL in", values, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelNotIn(List<String> values) {
            addCriterion("PUB_TEL not in", values, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelBetween(String value1, String value2) {
            addCriterion("PUB_TEL between", value1, value2, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubTelNotBetween(String value1, String value2) {
            addCriterion("PUB_TEL not between", value1, value2, "pubTel");
            return (Criteria) this;
        }

        public Criteria andPubLinkManIsNull() {
            addCriterion("PUB_LINK_MAN is null");
            return (Criteria) this;
        }

        public Criteria andPubLinkManIsNotNull() {
            addCriterion("PUB_LINK_MAN is not null");
            return (Criteria) this;
        }

        public Criteria andPubLinkManEqualTo(String value) {
            addCriterion("PUB_LINK_MAN =", value, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManNotEqualTo(String value) {
            addCriterion("PUB_LINK_MAN <>", value, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManGreaterThan(String value) {
            addCriterion("PUB_LINK_MAN >", value, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManGreaterThanOrEqualTo(String value) {
            addCriterion("PUB_LINK_MAN >=", value, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManLessThan(String value) {
            addCriterion("PUB_LINK_MAN <", value, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManLessThanOrEqualTo(String value) {
            addCriterion("PUB_LINK_MAN <=", value, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManLike(String value) {
            addCriterion("PUB_LINK_MAN like", value, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManNotLike(String value) {
            addCriterion("PUB_LINK_MAN not like", value, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManIn(List<String> values) {
            addCriterion("PUB_LINK_MAN in", values, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManNotIn(List<String> values) {
            addCriterion("PUB_LINK_MAN not in", values, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManBetween(String value1, String value2) {
            addCriterion("PUB_LINK_MAN between", value1, value2, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubLinkManNotBetween(String value1, String value2) {
            addCriterion("PUB_LINK_MAN not between", value1, value2, "pubLinkMan");
            return (Criteria) this;
        }

        public Criteria andPubIntroIsNull() {
            addCriterion("PUB_INTRO is null");
            return (Criteria) this;
        }

        public Criteria andPubIntroIsNotNull() {
            addCriterion("PUB_INTRO is not null");
            return (Criteria) this;
        }

        public Criteria andPubIntroEqualTo(String value) {
            addCriterion("PUB_INTRO =", value, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroNotEqualTo(String value) {
            addCriterion("PUB_INTRO <>", value, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroGreaterThan(String value) {
            addCriterion("PUB_INTRO >", value, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroGreaterThanOrEqualTo(String value) {
            addCriterion("PUB_INTRO >=", value, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroLessThan(String value) {
            addCriterion("PUB_INTRO <", value, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroLessThanOrEqualTo(String value) {
            addCriterion("PUB_INTRO <=", value, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroLike(String value) {
            addCriterion("PUB_INTRO like", value, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroNotLike(String value) {
            addCriterion("PUB_INTRO not like", value, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroIn(List<String> values) {
            addCriterion("PUB_INTRO in", values, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroNotIn(List<String> values) {
            addCriterion("PUB_INTRO not in", values, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroBetween(String value1, String value2) {
            addCriterion("PUB_INTRO between", value1, value2, "pubIntro");
            return (Criteria) this;
        }

        public Criteria andPubIntroNotBetween(String value1, String value2) {
            addCriterion("PUB_INTRO not between", value1, value2, "pubIntro");
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