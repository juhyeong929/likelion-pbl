const AssignmentAPI = {
    create(memberId, data) {
        return httpFetch(`/members/${memberId}/assignments`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        });
    },
    getAll() {
        return httpFetch('/assignments');
    },
    getByMember(memberId) {
        return httpFetch(`/members/${memberId}/assignments`);
    },
    getById(id) {
        return httpFetch(`/assignments/${id}`);
    },
    search(keyword) {
        return httpFetch(`/assignments/search?keyword=${encodeURIComponent(keyword)}`);
    },
    update(id, data) {
        return httpFetch(`/assignments/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        });
    },
    delete(id) {
        return httpFetch(`/assignments/${id}`, { method: 'DELETE' });
    },
};

const AssignmentUI = {
    container: null,

    initAssignmentUI() {
        this.container = document.getElementById('assignment-tab');
        this.container.innerHTML = `
            <div class="panel">
                <h3>과제 등록</h3>
                <div class="grid">
                    <label>멤버
                        <select id="assignment-member-id"></select>
                    </label>
                    <label>제목<input id="assignment-title" type="text"></label>
                    <label>설명<textarea id="assignment-description"></textarea></label>
                </div>
                <div class="actions">
                    <button id="assignment-create-btn" type="button">등록</button>
                </div>
            </div>

            <div class="panel">
                <h3>과제 조회 / 검색</h3>
                <div class="grid">
                    <label>멤버별 조회
                        <select id="assignment-filter-member-id"></select>
                    </label>
                    <label>단건 ID<input id="assignment-find-id" type="number"></label>
                    <label>제목 검색<input id="assignment-search-keyword" type="text"></label>
                </div>
                <div class="actions">
                    <button id="assignment-load-all-btn" type="button">전체 조회</button>
                    <button id="assignment-load-by-member-btn" type="button" class="secondary">멤버별 조회</button>
                    <button id="assignment-find-btn" type="button" class="secondary">단건 조회</button>
                    <button id="assignment-search-btn" type="button" class="secondary">제목 검색</button>
                </div>
            </div>

            <div class="panel">
                <h3>과제 수정 / 삭제</h3>
                <div class="grid">
                    <label>ID<input id="assignment-edit-id" type="number"></label>
                    <label>제목<input id="assignment-edit-title" type="text"></label>
                    <label>설명<textarea id="assignment-edit-description"></textarea></label>
                </div>
                <div class="actions">
                    <button id="assignment-load-edit-btn" type="button" class="secondary">불러오기</button>
                    <button id="assignment-save-btn" type="button">저장</button>
                    <button id="assignment-delete-btn" type="button" class="danger">삭제</button>
                </div>
            </div>

            <div class="panel">
                <h3>과제 목록</h3>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>제목</th>
                            <th>설명</th>
                            <th>멤버 ID</th>
                            <th>멤버 이름</th>
                        </tr>
                    </thead>
                    <tbody id="assignment-table-body"></tbody>
                </table>
            </div>
        `;

        document.getElementById('assignment-create-btn').addEventListener('click', () => this.createAssignment());
        document.getElementById('assignment-load-all-btn').addEventListener('click', () => this.loadAll());
        document.getElementById('assignment-load-by-member-btn').addEventListener('click', () => this.loadByMember());
        document.getElementById('assignment-find-btn').addEventListener('click', () => this.findAssignment());
        document.getElementById('assignment-search-btn').addEventListener('click', () => this.searchAssignments());
        document.getElementById('assignment-load-edit-btn').addEventListener('click', () => this.loadForEdit());
        document.getElementById('assignment-save-btn').addEventListener('click', () => this.saveAssignment());
        document.getElementById('assignment-delete-btn').addEventListener('click', () => this.deleteAssignment());
    },

    refreshMemberSelect(members) {
        const options = members.map((member) =>
            `<option value="${member.id}">${member.id} - ${member.name}</option>`
        ).join('');

        document.getElementById('assignment-member-id').innerHTML = options;
        document.getElementById('assignment-filter-member-id').innerHTML = options;
    },

    async createAssignment() {
        const memberId = document.getElementById('assignment-member-id').value;
        const payload = {
            title: document.getElementById('assignment-title').value.trim(),
            description: document.getElementById('assignment-description').value.trim(),
        };

        await AssignmentAPI.create(memberId, payload);
        showToast('과제가 등록되었습니다.', 'success');
        await this.loadAll();
    },

    async loadAll() {
        const assignments = await AssignmentAPI.getAll();
        this.renderAssignments(assignments);
    },

    async loadByMember() {
        const memberId = document.getElementById('assignment-filter-member-id').value;
        const assignments = await AssignmentAPI.getByMember(memberId);
        this.renderAssignments(assignments);
    },

    async findAssignment() {
        const id = document.getElementById('assignment-find-id').value;
        if (!id) return;
        const assignment = await AssignmentAPI.getById(id);
        this.renderAssignments([assignment]);
        this.fillEditForm(assignment);
    },

    async searchAssignments() {
        const keyword = document.getElementById('assignment-search-keyword').value.trim();
        if (!keyword) return;
        const assignments = await AssignmentAPI.search(keyword);
        this.renderAssignments(assignments);
    },

    async loadForEdit() {
        const id = document.getElementById('assignment-edit-id').value;
        if (!id) return;
        const assignment = await AssignmentAPI.getById(id);
        this.fillEditForm(assignment);
    },

    fillEditForm(assignment) {
        document.getElementById('assignment-edit-id').value = assignment.id;
        document.getElementById('assignment-edit-title').value = assignment.title;
        document.getElementById('assignment-edit-description').value = assignment.description;
    },

    async saveAssignment() {
        const id = document.getElementById('assignment-edit-id').value;
        if (!id) return;

        const payload = {
            title: document.getElementById('assignment-edit-title').value.trim(),
            description: document.getElementById('assignment-edit-description').value.trim(),
        };

        await AssignmentAPI.update(id, payload);
        showToast('과제가 수정되었습니다.', 'success');
        await this.loadAll();
    },

    async deleteAssignment() {
        const id = document.getElementById('assignment-edit-id').value;
        if (!id) return;

        await AssignmentAPI.delete(id);
        showToast('과제가 삭제되었습니다.', 'success');
        document.getElementById('assignment-edit-id').value = '';
        document.getElementById('assignment-edit-title').value = '';
        document.getElementById('assignment-edit-description').value = '';
        await this.loadAll();
    },

    renderAssignments(assignments) {
        const tbody = document.getElementById('assignment-table-body');
        tbody.innerHTML = assignments.map((assignment) => `
            <tr>
                <td>${assignment.id}</td>
                <td>${assignment.title}</td>
                <td>${assignment.description}</td>
                <td>${assignment.memberId}</td>
                <td>${assignment.memberName}</td>
            </tr>
        `).join('');
    },
};

function initAssignmentUI() {
    AssignmentUI.initAssignmentUI();
}
