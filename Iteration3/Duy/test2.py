import googlemaps
import pprint
#from datetime import datetime
import time
import os 
from bs4 import BeautifulSoup
import sys
#getting abstracted API key
with open('apiKey.txt','r') as file:
	APIKEY=file.readline()

#declare google map client
gmaps = googlemaps.Client(key = APIKEY)

#search nearby for places IDs
typeF=['food','cafe']
if(len(sys.argv)==1):
	locationLL = '33.8749965 -117.884496462'
	print("Default Location: "+locationLL)
else:
	locationLL = str(sys.argv[1])+ ", " + str(sys.argv[2])
	print("Current Location: "+locationLL)
places_results = gmaps.places_nearby(location=locationLL, radius =1200, open_now=False, type = 'cafe')

#pprint.pprint(places_results)

#using places id for google map deatils 
#for x in range(3):

for place in places_results['results']:
	my_place_id = place['place_id']
		

	#valid fields param for `place` are 'review', 'icon', 'type', 'photo', 'url', 'permanently_closed'
	#'id', 'utc_offset', 'scope', 'geometry', 'opening_hours', 'price_level', 'formatted_phone_number'
	#'formatted_address', 'address_component', 'plus_code', 'place_id', 'website', 'name', 'rating', 
	#'alt_id', 'international_phone_number', 'vicinity', 'adr_address'
	my_fields = ['name', 'formatted_phone_number', 'type','adr_address', 'rating', 'price_level','review']

	place_details = gmaps.place(place_id = my_place_id, fields= my_fields)

	#pprint.pprint(place_details)
	if  'name' in place_details['result']:
		print("Name: " + str(place_details['result']['name']))
	if  'formatted_phone_number' in place_details['result']:
		print("Phone Number: " + str(place_details['result']['formatted_phone_number']))
	if  'types' in place_details['result']:
		print("Type: " + str(place_details['result']['types']))
	if  'adr_address' in place_details['result']:
		soup = BeautifulSoup(place_details['result']['adr_address'], "html.parser")
		if soup.find("span", class_="street-address"):
			street=str(soup.find("span", class_="street-address").text)
		if soup.find("span", class_="locality"):			
			city=str(soup.find("span", class_="locality").text)
		if soup.find("span", class_="region"):	
			state=str(soup.find("span", class_="region").text)
		if soup.find("span", class_="postal-code"):	
			zipcode=str(soup.find("span", class_="postal-code").text)
		if soup.find("span", class_="country-name"):	
			country=str(soup.find("span", class_="country-name").text)
		print ("Address: " +street+ ", " + city + ", " + state + " " +zipcode)
	if  'rating' in place_details['result']:
		print("Overal Rating: " + str(place_details['result']['rating']))
	if  'price_level' in place_details['result']:
		print("Price Level: " + str(place_details['result']['price_level']))

	print()
	print("----------------------------------------------------------------------------------------------------------------------")
	
	#time.sleep(1)

	#place_results = gmaps.places_nearby(page_tokens = place_results['next_page_token'])































