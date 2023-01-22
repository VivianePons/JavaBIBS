
OUTPUTDIR=docs

SSH_HOST=pl-ssh.lri.fr
SSH_PORT=22
SSH_USER=pons
SSH_TARGET_DIR=WWW/static/JavaBIBS

OUTPUTDIR = docs
SRC = src
EXERCICES = exercices
CODE = code

CoursesNames=Cours1-IntroBase Cours2-Collections

TPNames=TP1

CoursesHTML=$(foreach course, $(CoursesNames), $(OUTPUTDIR)/$(course).html)
CoursesPDF = $(foreach course, $(CoursesNames), $(OUTPUTDIR)/pdf/$(course).pdf)
TPHTML = $(foreach TP, $(TPNames), $(OUTPUTDIR)/$(TP).html)

# rules

all: Courses $(OUTPUTDIR)/index.html TPs

Courses: $(CoursesPDF) $(CoursesHTML)

TPs: $(TPHTML)

$(OUTPUTDIR)/index.html: $(SRC)/index.md
	pandoc -s $(SRC)/index.md -o $(OUTPUTDIR)/index.html
	
$(OUTPUTDIR)/TP%.html: $(SRC)/TP%.md
	pandoc -s $(SRC)/$(basename $(notdir $@)).md -o $@ --css css/TP.css --toc --toc-depth=3
	
$(OUTPUTDIR)/pdf/%.pdf: $(SRC)/%.md
	cd $(OUTPUTDIR); \
	pandoc -t beamer ../$(SRC)/$(basename $(notdir $@)).md -o pdf/$(basename $(notdir $@)).pdf

$(OUTPUTDIR)/Cours%.html: $(SRC)/Cours%.md
	pandoc -t slidy -s $(SRC)/$(basename $(notdir $@)).md -o $@ --css slidy/custom.css 
	

rsync_upload: all
	rsync -e "ssh -p $(SSH_PORT)" -P -rvzc $(OUTPUTDIR)/ $(SSH_USER)@$(SSH_HOST):$(SSH_TARGET_DIR) --cvs-exclude
	

