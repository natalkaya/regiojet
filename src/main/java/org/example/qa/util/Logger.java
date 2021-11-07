package org.example.qa.util;

import org.slf4j.LoggerFactory;

abstract class Logger {
    org.slf4j.Logger log = LoggerFactory.getLogger(getClass().getName());
}
