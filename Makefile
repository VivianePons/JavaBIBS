
html/index.html: src/index.md
	pandoc -s src/index.md -o html/index.html
	
html/pdf/%.pdf: src/%.md
	cd html; \
	pandoc -t beamer ../src/$(basename $(notdir $@)).md -o pdf/$(basename $(notdir $@)).pdf

html/%.html: src/%.md
	pandoc -t slidy -s src/$(basename $(notdir $@)).md -o $@ --css slidy/custom.css 
	

