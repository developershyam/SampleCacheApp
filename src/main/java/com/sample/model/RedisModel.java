package com.sample.model;

import java.io.Serializable;

public abstract class RedisModel implements Serializable {

	public abstract String getId();

	public abstract void setId(String id);

}
