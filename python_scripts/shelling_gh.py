import subprocess
from pathlib import Path

# try os.system("echo Hello from the other side!") as a backup

# use as wd: ~/java_CI_scripts (only for testing)
# real case will have absolute paths from the beginning

list_files = subprocess.run(["java", "-version"])
print("The exit code was: %d" % list_files.returncode)

fiji_flags = ["--headless", "--ij2", "--console", "--run"]
exec_file = Path.home() / Path("blank_fiji", "Fiji.app", "ImageJ-win64.exe")
print("exec file is", exec_file)

#script = Path(".\\src\\reproduce\\test_1_with_deepimagej.clj")
script = Path(".\\src\\reproduce\\test_1_with_deepimagej.clj")
print("script path is", script)

#arg = "folder=\"C:/Users/hestevez/REPOS/CI-deepimagej-bioimage-io/java_CI_scripts/../models/10.5281/zenodo.6348084/6348085\""

folder = Path("../models/10.5281/zenodo.7786492/7786493")
folder_str = str(folder.absolute()).replace("\\","/")
arg = "folder='{}'".format(folder_str)
print("folder is",folder)

cmd = [exec_file] + fiji_flags+ [script, arg]

c = subprocess.run(cmd)

