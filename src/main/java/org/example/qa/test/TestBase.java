package org.example.qa.test;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public abstract class TestBase  {

    public final Logger log = LoggerFactory.getLogger(this.getClass().getName());

}
