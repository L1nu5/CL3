from selenium import webdriver
from selenium.webdriver.common.keys import Keys

driver = webdriver.Firefox()
driver.get("http://127.0.0.1:5000/")
elem1 = driver.find_element_by_name("text")
elem1.send_keys("My name  is husen Chawla")
elem2 = driver.find_element_by_name("sub")
elem2.send_keys(Keys.ENTER)
assert "100" in driver.page_source
driver.close() 

